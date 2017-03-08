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
		cfg.setNumWorkers(2);//����ʹ��������������
		cfg.setDebug(false);
		TopologyBuilder builder = new TopologyBuilder();
		//����sqout�Ĳ��жȺ�������������2��ִ��������������
		builder.setSpout("spout", new PWSpout(), 2);//.setNumTasks(2);
		//����bolt�Ĳ��жȺ�������:������2��ִ������4������
		builder.setBolt("print-bolt", new PrintBolt(), 2).shuffleGrouping("spout").setNumTasks(4);
		//����bolt�Ĳ��жȺ�������:������6��ִ������6������
		builder.setBolt("write-bolt", new WriteBolt(), 6).shuffleGrouping("print-bolt").setNumTasks(12);


		//1 ����ģʽ
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("top1", cfg, builder.createTopology());
		Thread.sleep(10000);
		cluster.killTopology("top1");
		cluster.shutdown();

		//2 ��Ⱥģʽ
//		StormSubmitter.submitTopology("top1", cfg, builder.createTopology());
		
	}
}
