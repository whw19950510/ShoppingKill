package com.web.web;

import com.web.dto.Exposer;
import com.web.dto.SecKillExecution;
import com.web.dto.SeckillResult;
import com.web.entity.SecKill;
import com.web.enums.SecKillStateEnum;
import com.web.exception.RepeatKillException;
import com.web.exception.SecKillCloseException;
import com.web.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seckill")
//映射模块名称
public class seckillController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SecKillService secKillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //jsp+model=ModelAndView
        List<SecKill> list = secKillService.getSeckillList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId")Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        SecKill secKill = secKillService.getById(seckillId);
        if(secKill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("secKill", secKill);
        return "detail";
    }

    // ajax JSON数据获取,泛型封装Exposer
    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public SeckillResult<Exposer> exposer(Long seckillId) {
        SeckillResult<Exposer> result;
        //DTO web和service之间的数据传递
        try {
            Exposer exposer = secKillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    //DTO是一种Web和数据库之间的数据传递
    //Phone是从Cookie里面获取到的
    @RequestMapping(value = "{seckillId}/", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SecKillExecution> execute(@PathVariable("seckillId") Long seckillId, @PathVariable("md5") String md5, @CookieValue(value = "killPhone", required = false) Long userPhone) {
        SeckillResult<SecKillExecution> result;
        if (userPhone == null) {
            return new SeckillResult<SecKillExecution>(false, "未注册");
        }
        try {
            SecKillExecution execution = secKillService.executeSeckill(seckillId, userPhone, md5);
            return new SeckillResult<SecKillExecution>(true, execution);
        } catch (RepeatKillException e) {
            SecKillExecution execution = new SecKillExecution(seckillId, SecKillStateEnum.REPEAT_KILL);
            return new SeckillResult<SecKillExecution>(false, execution);
        } catch (SecKillCloseException e) {
            SecKillExecution execution = new SecKillExecution(seckillId, SecKillStateEnum.END);
            return new SeckillResult<SecKillExecution>(false, execution);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            SecKillExecution execution = new SecKillExecution(seckillId, SecKillStateEnum.INNER_ERROR);
            return new SeckillResult<SecKillExecution>(false,execution);
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }
}
