package storm.topology;


import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import storm.bolt.PrintBolt;
import storm.bolt.WriteBolt;
import storm.spout.PWSpout;

public class PWTopology2 {

	public static void main(String[] args) throws Exception {


		Config cfg = new Config();
		cfg.setNumWorkers(2);//设置使用俩个工作进程
		cfg.setDebug(false);
		TopologyBuilder builder = new TopologyBuilder();
		//设置sqout的并行度和任务数（产生2个执行器和俩个任务）
		builder.setSpout("spout", new PWSpout(), 2);//.setNumTasks(2);
		//设置bolt的并行度和任务数:（产生2个执行器和4个任务）
		builder.setBolt("print-bolt", new PrintBolt(), 2).shuffleGrouping("spout").setNumTasks(4);
		//设置bolt的并行度和任务数:（产生6个执行器和6个任务）
		builder.setBolt("write-bolt", new WriteBolt(), 6).shuffleGrouping("print-bolt").setNumTasks(12);


		//1 本地模式
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("top1", cfg, builder.createTopology());
		Thread.sleep(10000);
		cluster.killTopology("top1");
		cluster.shutdown();

		//2 集群模式
//		StormSubmitter.submitTopology("top1", cfg, builder.createTopology());
		
	}
}
