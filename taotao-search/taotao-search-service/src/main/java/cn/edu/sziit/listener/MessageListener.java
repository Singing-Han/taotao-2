package cn.edu.sziit.listener;


import cn.edu.sziit.mapper.ItemMapper;
import cn.edu.sziit.pojo.Item;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/*
 *  @创建时间:  2020/12/29 8:41
 *  @描述：    TODO
 */

@Component
public class MessageListener {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrClient solrClient;

    @JmsListener(destination = "item")
    public void receiveSaveItemMsg(String id){

        System.out.println("搜索系统收到了消息：" + id);

        //1. 根据id 去查询mysql ，得到商品
        Item item = itemMapper.selectByPrimaryKey(Long.parseLong(id));

        System.out.println("item===" + item);

        //2. 根据商品，构建商品的索引
        //3. 把索引添加到solr里面去

        //2. 构建索引，追加到索引库当中
        // 2. 根据获取到的数据，保存索引库
        SolrInputDocument document = new SolrInputDocument();
        // 商品id
        document.setField("id", item.getId().toString());
        // 商品标题
        document.setField("item_title", item.getTitle());
        // 商品价格
        document.setField("item_price", item.getPrice());
        // 商品图片
        document.setField("item_image", item.getImage());
        // 商品类目id
        document.setField("item_cid", item.getCid());
        // 商品状态
        document.setField("item_status", item.getStatus());

        try {
            // 保存到索引库中
            solrClient.add(document);
            solrClient.commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "shelveItem")
    public void receiveShelveItemMsg(String id){

        System.out.println("搜索系统收到了消息：" + id);

        try {
            // 保存到索引库中
            solrClient.deleteById(id);
            solrClient.commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
