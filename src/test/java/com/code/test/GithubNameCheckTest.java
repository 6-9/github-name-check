package com.code.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Github 的用户名可用性测试
 *
 * @author wzh
 */
public class GithubNameCheckTest {

    String filepath = "D:\\logs233\\";//目录配置
    String checkName = filepath + "checkName3.txt";//生成的用户名列表文件
    String checkNameProgress = filepath + "checkName3Progress.txt";//进度文件
    String checkNameSuccess = filepath + "checkName3Success.txt";//可用的用户名列表文件

    /**
     * 生成的用户名列表
     */
    @Test
    public void generateName() {
        ArrayList<String> letter = new ArrayList<>();
        //字母
        char aa = 'a';
        for (int i = 0; i < 26; i++) {
            letter.add((char) (aa + i) + "");
        }

        //数字
        ArrayList<String> digit = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            digit.add(i + "");
        }

        //连字符
        String minus = "-";

        //开始和结束允许的字符
        ArrayList<String> allow = new ArrayList<>(letter);
        allow.addAll(digit);

        //允许的字符
        ArrayList<String> all = new ArrayList<>(letter);
        all.addAll(digit);
        all.add(minus);

        //name3位
        ArrayList<String> usernameList = new ArrayList<>();
        for (String s : allow) {
            for (String s1 : all) {
                for (String s3 : allow) {
                    String username = s + s1 + s3;
                    usernameList.add(username);
                }
            }
        }

        //生成到文件
        FileUtil.appendUtf8Lines(usernameList, checkName);
        Console.log("总生成数量：", usernameList.size());//47952

    }


    /**
     * 测试名称有效性
     */
    @Test
    public void checkName() {
        //获取列表
        List<String> list = FileUtil.readUtf8Lines(checkName);
        //获取进度
        Integer progress = 1;
        if (FileUtil.exist(checkNameProgress)) {
            String progressStr = FileUtil.readUtf8String(checkNameProgress);
            progress = Integer.valueOf(progressStr);
        }

        //有效数量
        int trueCount = 0;

        //获取数据
        for (int i = progress; i <= list.size(); i++) {

            String name = list.get(i);

            HttpRequest request = HttpUtil.createGet("https://github.com/signup_check/username?value=" + name);

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("accept", "*/*");
            hashMap.put("accept-encoding", "gzip,deflate,br,zstd");
            hashMap.put("accept-language", "zh-CN,zh;q=0.9");
            hashMap.put("cache-control", "no-cache");
            hashMap.put("cookie", "default-src 'none'; base-uri 'self'; child-src github.com/assets-cdn/worker/ github.com/webpack/ github.com/assets/ gist.github.com/assets-cdn/worker/; connect-src 'self' uploads.github.com www.githubstatus.com collector.github.com raw.githubusercontent.com api.github.com github-cloud.s3.amazonaws.com github-production-repository-file-5c1aeb.s3.amazonaws.com github-production-upload-manifest-file-7fdce7.s3.amazonaws.com github-production-user-asset-6210df.s3.amazonaws.com *.rel.tunnels.api.visualstudio.com wss://*.rel.tunnels.api.visualstudio.com objects-origin.githubusercontent.com copilot-proxy.githubusercontent.com proxy.individual.githubcopilot.com proxy.business.githubcopilot.com proxy.enterprise.githubcopilot.com *.actions.githubusercontent.com wss://*.actions.githubusercontent.com productionresultssa0.blob.core.windows.net/ productionresultssa1.blob.core.windows.net/ productionresultssa2.blob.core.windows.net/ productionresultssa3.blob.core.windows.net/ productionresultssa4.blob.core.windows.net/ productionresultssa5.blob.core.windows.net/ productionresultssa6.blob.core.windows.net/ productionresultssa7.blob.core.windows.net/ productionresultssa8.blob.core.windows.net/ productionresultssa9.blob.core.windows.net/ productionresultssa10.blob.core.windows.net/ productionresultssa11.blob.core.windows.net/ productionresultssa12.blob.core.windows.net/ productionresultssa13.blob.core.windows.net/ productionresultssa14.blob.core.windows.net/ productionresultssa15.blob.core.windows.net/ productionresultssa16.blob.core.windows.net/ productionresultssa17.blob.core.windows.net/ productionresultssa18.blob.core.windows.net/ productionresultssa19.blob.core.windows.net/ github-production-repository-image-32fea6.s3.amazonaws.com github-production-release-asset-2e65be.s3.amazonaws.com insights.github.com wss://alive.github.com api.githubcopilot.com api.individual.githubcopilot.com api.business.githubcopilot.com api.enterprise.githubcopilot.com; font-src github.githubassets.com; form-action 'self' github.com gist.github.com copilot-workspace.githubnext.com objects-origin.githubusercontent.com; frame-ancestors 'none'; frame-src viewscreen.githubusercontent.com notebooks.githubusercontent.com; img-src 'self' data: blob: github.githubassets.com media.githubusercontent.com camo.githubusercontent.com identicons.github.com avatars.githubusercontent.com private-avatars.githubusercontent.com github-cloud.s3.amazonaws.com objects.githubusercontent.com secured-user-images.githubusercontent.com/ user-images.githubusercontent.com/ private-user-images.githubusercontent.com opengraph.githubassets.com github-production-user-asset-6210df.s3.amazonaws.com customer-stories-feed.github.com spotlights-feed.github.com objects-origin.githubusercontent.com *.githubusercontent.com; manifest-src 'self'; media-src github.com user-images.githubusercontent.com/ secured-user-images.githubusercontent.com/ private-user-images.githubusercontent.com github-production-user-asset-6210df.s3.amazonaws.com gist.github.com; script-src github.githubassets.com; style-src 'unsafe-inline' github.githubassets.com; upgrade-insecure-requests; worker-src github.com/assets-cdn/worker/ github.com/webpack/ github.com/assets/ gist.github.com/assets-cdn/worker/");
            hashMap.put("pragma", "no-cache");
            hashMap.put("priority", "u=1, i");
            hashMap.put("referer", "https://github.com/signup?return_to=https%3A%2F%2Fgithub.com%2Fnew&source=login");
            hashMap.put("sec-ch-ua", "\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"");
            hashMap.put("sec-ch-ua-mobile", "?0");
            hashMap.put("sec-ch-ua-platform", "\"Windows\"");
            hashMap.put("sec-fetch-dest", "empty");
            hashMap.put("sec-fetch-mode", "cors");
            hashMap.put("sec-fetch-site", "same-origin");
            hashMap.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36");

            request.addHeaders(hashMap);
            HttpResponse response = request.execute();
            String body = response.body();

            String flag = "";

            if (body.contains("is available.")) {
//            if (true) {//测试生成成功数据
                flag = "true";
                trueCount++;

                //设置成功数据
                FileUtil.appendString(name + "\r\n", checkNameSuccess, CharsetUtil.CHARSET_UTF_8);

            } else if (body.contains("is not available.") || body.contains("is unavailable.")) {
                flag = "fail";

            } else {
                flag = "error";

                Console.error("发生了错误:", name);
                Console.log(body);

                //发生了错误，休息一会
                try {
                    int sleep = 1000 * 60 * 6;
                    Thread.sleep(sleep);
                    Console.log("error sleep ", sleep);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String data = i + "\t" + name + "\t" + flag;
            Console.log(data);

            Console.log("进度:", i + "/" + list.size(), "有效:", trueCount);

            //设置进度
            FileUtil.writeUtf8String(i + "", checkNameProgress);

            //随机，休息一会
            try {
                int randomInt = RandomUtil.randomInt(1000, 3000);
                Thread.sleep(randomInt);
                Console.log("random sleep ", randomInt);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
