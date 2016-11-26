package com.fanxl.studymvc.web;

import com.fanxl.studymvc.dto.Exposer;
import com.fanxl.studymvc.dto.SeckillExecution;
import com.fanxl.studymvc.dto.SeckillResult;
import com.fanxl.studymvc.entity.Seckill;
import com.fanxl.studymvc.exception.RepeatKillException;
import com.fanxl.studymvc.exception.SeckillCloseException;
import com.fanxl.studymvc.enums.SeckillStateEnum;
import com.fanxl.studymvc.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by fanxl2 on 2016/11/21.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){

        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);

        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if (seckillId==null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill==null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    // ajax json接口
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> seckillResult;

        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            seckillResult = new SeckillResult<Exposer>(true, exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            seckillResult = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return seckillResult;
    }

    // /seckill/1000/fadd652b5e105b816145afd12772d98b/execution

    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @CookieValue(value = "killPhone",required = false) Long userPhone,
                                                   @PathVariable("md5")String md5){

        logger.info("我要秒杀了");

        SeckillResult<SeckillExecution> seckillResult;
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId,userPhone,md5);
            seckillResult = new SeckillResult<SeckillExecution>(true,seckillExecution);
        }catch (RepeatKillException e){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
            seckillResult = new SeckillResult<SeckillExecution>(true, seckillExecution);
        }catch (SeckillCloseException e){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.END);
            seckillResult = new SeckillResult<SeckillExecution>(true, seckillExecution);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
            seckillResult = new SeckillResult<SeckillExecution>(true, seckillExecution);
        }
        return seckillResult;
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> now(){
        Date date = new Date();
        return new SeckillResult<Long>(true, date.getTime());
    }

}
