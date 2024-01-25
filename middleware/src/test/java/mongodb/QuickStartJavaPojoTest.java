package mongodb;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import mongodb.entity.Address;
import mongodb.entity.Favorites;
import mongodb.entity.User;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.addEachToSet;
import static com.mongodb.client.model.Updates.set;

//原生java驱动 Pojo的操作方式
public class QuickStartJavaPojoTest {
    private MongoDatabase db;
    private MongoCollection<User> doc;
    private MongoClient client;

    @Before
    public void init(){
        //编解码器的list
        List<CodecRegistry> codecResgistes = new ArrayList<>();
        //list加入默认的编解码器集合
        codecResgistes.add(MongoClient.getDefaultCodecRegistry());
        //生成一个pojo的编解码器
        CodecRegistry pojoCodecRegistry = CodecRegistries.
                fromProviders(PojoCodecProvider.builder().automatic(true).build());
        //list加入pojo的编解码器
        codecResgistes.add(pojoCodecRegistry);
        //通过编解码器的list生成编解码器注册中心
        CodecRegistry registry = CodecRegistries.fromRegistries(codecResgistes);
        //把编解码器注册中心放入MongoClientOptions
        //MongoClientOptions相当于连接池的配置信息
        MongoClientOptions build = MongoClientOptions.builder().codecRegistry(registry).build();

        ServerAddress serverAddress = new ServerAddress("127.0.0.1", 27017);

        client = new MongoClient(serverAddress, build);
        db =client.getDatabase("lijin");
        doc = db.getCollection("users",User.class);
    }



    @Test
    public void insertDemo(){
        User user1 = new User();
        user1.setUsername("lijin");
        user1.setCountry("China");
        user1.setAge(36);
        user1.setLenght(178.75f);
        user1.setSalary(new BigDecimal("16565.22"));

        //添加“address”子文档
        Address address1 = new Address();
        address1.setaCode("0000");
        address1.setAdd("xxx000");
        user1.setAddress(address1);

        //添加“favorites”子文档，其中两个属性是数组
        Favorites favorites1 = new Favorites();
        favorites1.setCites(Arrays.asList("北京", "南京"));
        favorites1.setMovies(Arrays.asList("爱死机", "光环"));
        user1.setFavorites(favorites1);


        User user2 = new User();
        user2.setUsername("yan");
        user2.setCountry("China");
        user2.setAge(30);
        user2.setLenght(185.75f);
        user2.setSalary(new BigDecimal("38888.22"));
        Address address2 = new Address();
        address2.setaCode("411000");
        address2.setAdd("我的地址2");
        user2.setAddress(address2);
        Favorites favorites2 = new Favorites();
        favorites2.setCites(Arrays.asList("西藏", "三亚"));
        favorites2.setMovies(Arrays.asList("西游记", "东游记"));
        user2.setFavorites(favorites2);

        User user3 = new User();
        user3.setUsername("mic");
        user3.setCountry("USA");
        user3.setAge(60);
        user3.setLenght(180.75f);
        user3.setSalary(new BigDecimal("3008888.22"));
        Address address3 = new Address();
        address3.setaCode("411000");
        address3.setAdd("我的地址3");
        user3.setAddress(address3);
        Favorites favorites3 = new Favorites();
        favorites3.setCites(Arrays.asList("纽约", "洛杉矶"));
        favorites3.setMovies(Arrays.asList("卓别林", "牛顿的棺材板"));
        user3.setFavorites(favorites3);


        //使用insertMany插入多条数据
        doc.insertMany(Arrays.asList(user1,user2,user3));

    }

    //查询出文档集合中所有记录  select * from  table
    @Test
    public void testFindAll() {
        Consumer<User> printDocument = new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user.toString());
            }
        };
        FindIterable<User> find = doc.find();
        find.forEach(printDocument);
    }
    //查询出文档集合中的记录（过滤1）
    @Test
    public void testFindFilter1() {
        //block接口专门用于处理查询出来的数据
        Consumer<User> printDocument = new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user.toString());
            }
        };
        //定义数据过滤器，喜欢的城市中要包含"北京"、"南京"
        Bson all = all("favorites.cites", Arrays.asList("北京", "南京"));
        FindIterable<User> find = doc.find(all);
        find.forEach(printDocument);
    }

    //查询出文档集合中的记录（过滤2）
    @Test
    public void testFindFilter2() {
        //block接口专门用于处理查询出来的数据
        Consumer<User> printDocument = new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user.toString());
            }
        };
        //定义数据过滤器，country like '%ina%'  and ( contry= 北京 or contry = USA)
        String regexStr = ".*ina.*";
        Bson regex = regex("country", regexStr);
        Bson or = or(eq("favorites.cites", "北京"), eq("favorites.cites", "纽约"));
        Bson and = and(regex, or);
        FindIterable<User> find = doc.find(and);
        find.forEach(printDocument);
    }

    @Test
    public void testUpdate1() {
        Bson eq = eq("username", "lijin");//定义数据过滤器，username = 'cang'
        Bson set = set("age", 18);//更新的字段.来自于Updates包的静态导入
        UpdateResult updateMany = doc.updateMany(eq, set);
        //打印受影响的行数
        System.out.println("------------------>" + String.valueOf(updateMany.getModifiedCount()));


        Bson eq2 = eq("username", "yan");//定义数据过滤器，

        Bson addEachToSet = addEachToSet("favorites.movies", Arrays.asList("小电影1 ", "小电影2"));
        UpdateResult updateMany2 = doc.updateMany(eq2, addEachToSet);
        System.out.println("------------------>" + String.valueOf(updateMany2.getModifiedCount()));
    }

    @Test
    public void testDelete() {

        Bson eq = eq("username", "yan");//定义数据过滤器，username='yan'
        DeleteResult deleteMany = doc.deleteMany(eq);
        System.out.println("------------------>" + String.valueOf(deleteMany.getDeletedCount()));//打印受影响的行数

        //定义数据过滤器，age > 50，所有过滤器的定义来自于Filter这个包的静态方法，需要频繁使用所以静态导入
        Bson gt = gt("age", 50);
        //定义数据过滤器，age < 100
        Bson lt = lt("age", 100);
        Bson and = and(gt, lt);//定义数据过滤器，将条件用and拼接
        DeleteResult deleteMany2 = doc.deleteMany(and);
        System.out.println("------------------>" + String.valueOf(deleteMany2.getDeletedCount()));//打印受影响的行数
    }


}


