package storm.trident;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.operation.builtin.Count;
import org.apache.storm.trident.testing.FixedBatchSpout;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.junit.Test;

/**
 * <dl>
 * <dt>Storm1</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/8</dd>
 * </dl>
 *
 * @author cuihc
 */
public class Storm1 {

    @Test
    public void run() throws InterruptedException {
        FixedBatchSpout spout = new FixedBatchSpout(new Fields("sentence"), 3,
                new Values("the cow jumped over the moon"),
                new Values("the man went to the store and bought some candy"),
                new Values("four score and seven years ago"),
                new Values("how many apples can you eat"));
        spout.setCycle(false);

        TridentTopology topology = new TridentTopology();
       topology.newStream("spout1", spout)
//               .shuffle()
                .each(new Fields("sentence"),new Split(),new Fields("word"))
                .groupBy(new Fields("word"))
//                .persistentAggregate(new MemoryMapState.Factory(), new Count(), new Fields("count"))
                .aggregate(new Count(), new Fields("count"))   //聚合
                .each(new Fields("word","count"), new ResultFunctiion(), new Fields())
                .parallelismHint(6); //并行度
        Config cfg = new Config();
        cfg.setNumWorkers(2);//设置使用俩个工作进程
        cfg.setDebug(false);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("top1", cfg, topology.build());
        Thread.sleep(10000);
        cluster.killTopology("top1");
        cluster.shutdown();
    }
}

class Split extends BaseFunction {

    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
        String sentence = tuple.getString(0);
        for (String word : sentence.split(" ")) {
            collector.emit(new Values(word));
        }
    }
}

class ResultFunctiion extends BaseFunction {
    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
        String word = tuple.getStringByField("word");
        Long count = tuple.getLongByField("count");
        System.out.println(word + ": " + count);

    }
}
