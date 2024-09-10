package com.example.controller;

import com.example.common.Result;
import com.example.entity.Acquireanswer;
import com.example.entity.Ahaveanswer;
import com.example.service.AcquireanswerService;
import com.example.service.AhaveanswerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;

/**
 * 需要回答答案的表前端操作接口
 **/
@RestController
@RequestMapping("/bigmodel")
public class FurinaBigModelController {

    @Resource
    private AcquireanswerService acquireanswerService;
    @Resource
    private AhaveanswerService    ahaveanswerService;
//    /**
//     * 新增 private Integer id;
//     *     /** 问题的文本信息 */
//     *
//    private String questiontext;
//     *     /** 提升 */
//             *
//    private String prompt;
//     *     /** agent */
//             *
//    private String agent;
//     *     /** 问问题的时间 */
//             *
//    private Date asktime;
//     *     /** 当前的轮状状态 */
//             *
//    private Integer genestatus;
//     *     /** fakeanswer */
//             *
//    private String answer;
//     *     /** 实体表里面匹配到的东西，当前需求不高 */
//             *
//    private String tag;
//     *     /**  */
//             *
//    private Integer parentid;
//     *     /** 上下文相关，其实目前这个也是摆设 */
//             *
//    private String context;
//     *     /** 用户满意度 */
//             *
//    private Integer satisfaction;
//     *     /** 附件，目前没有用 */
//             *
//    private String attachment;
//     */
//
    @PostMapping("/add")
    public Result add(@RequestBody Map<String,String> jsonMap) {
        String question =jsonMap.get("query");
//这个地方使用一个函数返回pareentid和attachment;

        //构建acquireanswer

        Acquireanswer acquireanswer = buildAcquireanswerFromRequest(question);
        acquireanswerService.add(acquireanswer);
//        private Integer id;
//        /** 问题的文本信息 */
//        private String questiontext;
//        /** 提升 */
//        private String prompt;
//        /** agent */
//        private String agent;
//        /** 问问题的时间 */
//        private Date asktime;
//        /** 当前的轮状状态 */
//        private Integer genestatus;
//        /** 问题被回答的时间 */
//        private Date answertime;
//        /** 问题的真是回答 */
//        private String answer;
//        /** 实体表里面匹配到的东西，当前需求不高 */
//        private String tag;
//        /** 父问题ID */
//        private Integer parentid;
//        /** 上下文相关，其实目前这个也是摆设 */
//        private String context;
//        /** 用户满意度 */
//        private Integer satisfaction;
//        /** 附件，目前没有用 */
//        private String attachment;      ahaveanswer的字段
        Ahaveanswer ahaveanswer = createAhaveanswerFromAcquireanswer(acquireanswer);
        //滴哦用一个函数   传入一个AcquireanswerF对象   返回一个ahaveanswer对象
        ahaveanswerService.add(ahaveanswer);

        return Result.success(ahaveanswer.getId());
    }
    private Ahaveanswer createAhaveanswerFromAcquireanswer(Acquireanswer acquireanswer) {
        Ahaveanswer ahaveanswer = new Ahaveanswer();
        // 根据 Acquireanswer 对象设置 Ahaveanswer 对象的字段
        // ahaveanswer.setId(acquireanswer.getId()); 假设需要设置 ID
        ahaveanswer.setQuestiontext(acquireanswer.getQuestiontext());
        ahaveanswer.setPrompt(acquireanswer.getPrompt());
        ahaveanswer.setAgent(acquireanswer.getAgent());
        ahaveanswer.setAsktime(acquireanswer.getAsktime());
        ahaveanswer.setAttachment(acquireanswer.getAttachment());
        ahaveanswer.setGenestatus(acquireanswer.getGenestatus());
        ahaveanswer.setParentid(acquireanswer.getParentid());
        // ... 设置其他字段
        return ahaveanswer;
    }
    private Acquireanswer buildAcquireanswerFromRequest(String questionText) {
        Acquireanswer acquireanswer = new Acquireanswer();
        // 这里需要根据实际的字段来赋值
        acquireanswer.setQuestiontext(questionText);
        acquireanswer.setAgent("计算机网络问题，好好回答！");
        acquireanswer.setAsktime(new Date());
        acquireanswer.setAttachment("student");
        acquireanswer.setGenestatus(0);
        acquireanswer.setPrompt("好好回答！");
        acquireanswer.setTag("NETWORK");
        acquireanswer.setParentid(2);
        // 设置其他字段...
        return acquireanswer;
    }
    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        acquireanswerService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        acquireanswerService.deleteBatch(ids);
        return Result.success();
    }
@PostMapping("/returnanswer")
public Result putanswers(@RequestBody Map<String,String> jsonMap) {
        //拿到putid和answer   调用ahaveanswer的更新    往对应的id里面写入answer
    String putid = jsonMap.get("putid");
    String answer = jsonMap.get("answer");

    // 校验putid和answer是否不为空
    if (putid == null || answer == null) {
        return Result.error("400","Putid or answer must not be null");
    }

    // 调用服务层的更新方法，传入putid和answer
    boolean updateResult = ahaveanswerService.updateAnswerById(putid, answer);

    // 根据更新结果返回相应的信息
    if (updateResult) {
        return Result.success("Answer updated successfully");
    } else {
        return Result.error("400","Failed to update answer");
    }
}
//getanswer   Get接口  根据id获得ahaveanswer对象
    /**
     * 修改
     */
    @GetMapping("/getanswer")
    public Result getanswer(@RequestParam("requestid") String requestid) {
        // 将String类型的requestid转换为Integer类型
        Integer id = Integer.parseInt(requestid);
        Ahaveanswer acquireanswer = ahaveanswerService.selectById(id);
        Map<String, Object> data = new HashMap<>();
        data.put("answer", acquireanswer.getAnswer());

        // 返回封装了Map的Result对象
        return Result.success(data);
    }
    @PutMapping("/update")
    public Result updateById(@RequestBody Acquireanswer acquireanswer) {
        acquireanswerService.updateById(acquireanswer);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Acquireanswer acquireanswer = acquireanswerService.selectById(id);
        return Result.success(acquireanswer);
    }
    @GetMapping("/selectBygenstatus")
    public Result selectByBygenstatus() {
       List< Acquireanswer> acquireanswers = acquireanswerService.selectBygenStatus();
        List<Map<String, Object>> acquireanswersModified = new ArrayList<>();

        for (Acquireanswer aa : acquireanswers) {
            // 创建一个新Map，只包含需要的字段
            Map<String, Object> aaMap = new LinkedHashMap<>();
            aaMap.put("id", aa.getId());
            aaMap.put("questiontext", aa.getQuestiontext());
            aaMap.put("prompt", aa.getPrompt());
            aaMap.put("agent", aa.getAgent());
            aaMap.put("asktime", aa.getAsktime()); // 确保asktime是正确的格式
            aaMap.put("genestatus", aa.getGenestatus());
            aaMap.put("tag", aa.getTag());
            aaMap.put("parentid", aa.getParentid());
            aaMap.put("satisfaction", aa.getSatisfaction());
            aaMap.put("attachment", aa.getAttachment());
            aaMap.put("putid",aa.getId());
            // 排除answer和context字段

            // 将Map添加到列表中
            acquireanswersModified.add(aaMap);
        }

        // 使用ObjectMapper将List<Map>转换为JSON字符串
        ObjectMapper objectMapper = new ObjectMapper();
     //   String jsonResponse = objectMapper.writeValueAsString(acquireanswersModified);

        // 返回JSON响应

        return Result.success(acquireanswersModified);
    }
//    {
//        "id": 1,
//            "questiontext": "我喜欢你",
//            "prompt": "好好回答！",
//            "agent": "计算机网络问题，好好回答！",
//            "asktime": "2024-08-15T23:07:45.000+00:00",
//            "genestatus": 0,
//            "answer": null,
//            "tag": "NETWORK",
//            "parentid": 2,
//            "context": null,
//            "satisfaction": 100,
//            "attachment": "student"
//    }
    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Acquireanswer acquireanswer) {
        List<Acquireanswer> list = acquireanswerService.selectAll(acquireanswer);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Acquireanswer acquireanswer,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Acquireanswer> page = acquireanswerService.selectPage(acquireanswer, pageNum, pageSize);
        return Result.success(page);
    }

}