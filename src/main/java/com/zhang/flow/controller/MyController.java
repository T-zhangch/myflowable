package com.zhang.flow.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.flow.event.UserEvent;
import com.zhang.flow.service.ExcelBean;
import com.zhang.flow.service.User;
import com.zhang.flow.service.UserExportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class MyController {

    @Autowired
    private UserExportService userExportService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ThreadPoolTaskExecutor jobExecutor;

    /**
     * 分页导出，每页10000条
     *
     * @param response
     */
    @RequestMapping("/export3")
    public void export3(HttpServletResponse response) throws IOException {
        int pageSize = 10000;
        int count = userExportService.count();
        LambdaQueryChainWrapper<User> lambdaQuery = userExportService.lambdaQuery();
        Page<User> pageLs = lambdaQuery.page(new Page<>(0, pageSize));
        List<User> records = pageLs.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            log.info("导出无数据");
            return;
        }
        try (ExcelWriter writer = EasyExcel.write(response.getOutputStream(), User.class).build()) {
            if (count > pageSize) {
                // 分页导出
                // 要分几页
                int size = count / pageSize;
                for (int i = 0; i < size; i++) {
                    WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).build();
                    Page<User> page = new Page<>();
                    page.setCurrent(i + 1);
                    page.setSize(pageSize);
                    Page<User> page1 = lambdaQuery.page(page);
                    writer.write(page1.getRecords(), writeSheet);
                    log.info("第几页导出完成{}", i);
                }
            }
        }

    }

    @RequestMapping("/export")
    public String export(HttpServletResponse response) throws IOException {
        long s1 = System.currentTimeMillis();
        File f = new File("D:\\abc\\excel.xlsx");
        if (!f.exists()) {
            f.createNewFile();
        }
        try {
            int count = userExportService.count();
            System.out.println(count);
            int pageSize = 100000;
            int current = 0;

            if (count < pageSize) {
                List<User> records = userExportService.page(new Page<User>(current, pageSize)).getRecords();
                EasyExcel.write(f, ExcelBean.class)
                        .sheet("sheet1")
                        .doWrite(() -> {
                            return records;
                        });
            } else {
                for (int i = 0; i < count / pageSize; i++) {
                    List<User> records = userExportService.page(new Page<User>(current, pageSize)).getRecords();
                    EasyExcel.write(f, ExcelBean.class)
                            .sheet("sheet1")
                            .doWrite(() -> {
                                return records;
                            });
                    current = current + pageSize;
                }
            }

        } catch (Exception e) {
            log.error("导出异常", e);
        }
        userExportService.export();
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
        return "导出完成";
    }

    @RequestMapping("/add")
    @Transactional
    public String add(User user) {
        userExportService.save(user);
        // 发布监听事件，当用户新建完成后，执行监听器中的内容
        applicationEventPublisher.publishEvent(new UserEvent<>(user));
        jobExecutor.execute(() -> {
            log.info("*****----------调用线程池做了其他的工作----------*******");
        });
        return "新增成功";
    }

    @RequestMapping("/get/{id}")
    public User getUserInfo(@PathVariable("id") Long id) {
        LambdaQueryChainWrapper<User> userLambdaQueryChainWrapper = userExportService.lambdaQuery();
        User user = userExportService.getById(id);
        User user1 = userExportService.getById(id);
        User user2 = userExportService.getById(id);
        User user3 = userExportService.getById(id);
        System.out.println("123124------------------------------");
        return user;
    }

}
