package com.example.myapplication.slice;

import com.example.myapplication.BookStore;
import com.example.myapplication.ResourceTable;
import com.example.myapplication.User;
import com.example.sqlitelibrary.DBManage;
import com.example.sqlitelibrary.DBOrmContext;
import com.example.sqlitelibrary.utils.Log;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;
import ohos.data.rdb.RdbStore;
import ohos.data.rdb.ValuesBucket;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {

    private DatabaseHelper helper;
    private RdbStore store;
    private  OrmContext context;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        helper = new DatabaseHelper(this);
        DBManage dbManger = new DBManage("user.db","user");
        context = dbManger.getConnectionContext(helper, BookStore.class);
//         DBManage dbManger = new DBManage("user.db");
//         store = dbManger.getConnectionStore(helper,"user");
        Button btnInsert = (Button) findComponentById(ResourceTable.Id_btn_insert);
        Button btnQuery = (Button) findComponentById(ResourceTable.Id_btn_query);
        Button btnDelete = (Button) findComponentById(ResourceTable.Id_btn_delete);
        Button btnUpdate = (Button) findComponentById(ResourceTable.Id_btn_update);
        btnInsert.setClickedListener(this::onClick);
        btnQuery.setClickedListener(this::onClick);
        btnDelete.setClickedListener(this::onClick);
        btnUpdate.setClickedListener(this::onClick);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    public void onClick(Component component) {
//        RdbStoreManage rdbStoreMange = new RdbStoreManage();
//        ValuesBucket values = new ValuesBucket();
//        values.putInteger("id", 1);
//        values.putString("name", "zhangsan");
//        values.putInteger("age", 18);
//        values.putDouble("salary", 100.5);
//        values.putByteArray("blobType", new byte[] {1, 2, 3});
//        rdbStoreMange.setSql(store, "insert into user values(zhangsan, 18, 100.5, byte[1,2,3])");
//        long id = rdbStoreMange.insert(store,"user", values);
//        System.out.println(id);

        DBOrmContext dbOrmContext = new DBOrmContext();
        switch (component.getId()) {
            case ResourceTable.Id_btn_insert: //插入数据
                //第一次使用user对应的表的时候，如果有这张表就直接使用，没有就创建表
                User user = new User();
                user.setFirstName("Zhang");
                user.setLastName("San");
                user.setAge(29);
                user.setBalance(100.51);
                boolean b = dbOrmContext.insert(context, user);
                Log.i("插入成功");
                System.out.println(b);
                break;
            case ResourceTable.Id_btn_query: //条件查询
                List<User> users = new ArrayList<>();
                OrmPredicates query = context.where(User.class).equalTo("lastName", "San");
                users = dbOrmContext.query(context, query);
                break;
            case ResourceTable.Id_btn_delete: //条件删除
                OrmPredicates delete = context.where(User.class).equalTo("lastName", "San");
                int delete1 = dbOrmContext.delete(context, delete);
                System.out.println(delete1);
                break;
            case ResourceTable.Id_btn_update: //条件更新
                ValuesBucket valuesBucket = new ValuesBucket();
                valuesBucket.putInteger("age", 31);
                valuesBucket.putString("firstName", "Zhang");
                valuesBucket.putString("lastName", "San");
                valuesBucket.putDouble("balance", 300.51);
                OrmPredicates update = context.where(User.class).equalTo("userId", 1);
                int update1 = dbOrmContext.update(context, valuesBucket, update);
                System.out.println(update1);
                break;
        }
        dbOrmContext.flush(context);
    }
}
