package gr.unirico.mcfapp;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.Topic;
import gr.unirico.mcflib.util.FileUtil;

@SpringBootApplication
public class Application {

	@Autowired
	McfApi api;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =  SpringApplication.run(Application.class, args);
		Application app = ctx.getBean(Application.class);
		app.startUpTask();
	}

	public void startUpTask() {
		final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		logger.info("Creating test data");
		String dir = api.getDataDir();
		FileUtil.deleteFiles(dir);
		try {
			Topic t;
			Comment c;
			t = api.newTopic("インバータの仕組みとは？", "https://www.youtube.com/watch?v=Scrik1grDK4&t=80s");
			c = api.newComment("テストユーザ1", "コメント1");
			t.add(c);
			Thread.sleep(100);
			c = api.newComment("テストユーザ2", "コメント2");
			t.add(c);
			api.writeTopic(t);
			api.archiveTopic(t);
			Thread.sleep(100);
			t = api.newTopic("ダイオードの仕組みとは？", "https://www.youtube.com/watch?v=__IfdtfnuPs");
			c = api.newComment("テストユーザ1", "コメント1");
			t.add(c);
			Thread.sleep(100);
			c = api.newComment("テストユーザ2", "コメント2");
			t.add(c);
			api.writeTopic(t);
			api.archiveTopic(t);
			Thread.sleep(100);
			t = api.newTopic("トランジスタの仕組みとは？", "https://www.youtube.com/watch?v=GmN_uR1BK2U");
			c = api.newComment("テストユーザ1", "コメント1");
			t.add(c);
			Thread.sleep(100);
			c = api.newComment("テストユーザ2", "コメント2");
			t.add(c);
			api.writeTopic(t);
			api.archiveTopic(t);
			Thread.sleep(100);
			t = api.newTopic("コインチェックからのNEM流出 発表された調査結果の疑問点（楠正憲）", "https://newspicks.com/news/2876707/");
			c = api.newComment("テストユーザ1", "コメント1");
			t.add(c);
			Thread.sleep(100);
			c = api.newComment("テストユーザ2", "コメント2");
			t.add(c);
			api.writeTopic(t);
			Thread.sleep(100);
			t = api.newTopic("コインチェック流出：攻撃者アカウントから、第三者にNEM送信か", "https://newspicks.com/news/2789847/");
			c = api.newComment("テストユーザ1", "コメント1");
			t.add(c);
			Thread.sleep(100);
			c = api.newComment("テストユーザ2", "コメント2");
			t.add(c);
			api.writeTopic(t);
			Thread.sleep(100);
			t = api.newTopic("なぜ？ 顧客たちが「それでもコインチェックを応援する」と語る理由", "https://newspicks.com/news/2876529/");
			c = api.newComment("テストユーザ1", "コメント1");
			t.add(c);
			Thread.sleep(100);
			c = api.newComment("テストユーザ2", "コメント2");
			t.add(c);
			api.writeTopic(t);
			logger.info("Complete test data");
		} catch (Exception e) {
			logger.error("Error in startUpTask", e);
		}
	}
}