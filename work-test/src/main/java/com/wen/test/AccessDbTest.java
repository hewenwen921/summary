package com.wen.test;

//import com.healthmarketscience.jackcess.*;
//import org.hsqldb.types.Types;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

/**
 * 连接Access数据库，数据操作测试
 * jdk1.7以后，rt.jar包，不再支持sun.jdbc.odbc.JdbcOdbcDriver驱动
 * 如果路径中出现乱码需要转码
 * 依赖jar:jackcess-encrypt-2.1.1.jar,ucanaccess-3.0.3.1.jar
 */
public class AccessDbTest {

    /**
     * 数据库文件,这里是Ubuntu系统
     */
    static String FILE_PATH;

    /**
     * 静态块，查找数据库文件路径
     */
    static {
        FILE_PATH = AccessDbTest.class.getResource("/db/test.accdb").getPath();
    }

//    public static void main(String[] args) throws IOException {
//        AccessDbTest.getAccessDataTable();
////        AccessTest.createTable();
//    }

    /**
     * 读取指定表格
     *
     * @throws IOException
     */
//    public static void getAccessDataTable() throws IOException {
//        Table table = DatabaseBuilder.open(new File(FILE_PATH)).getTable("class");
//        for (Row row : table) {
//            System.out.println("--ID--" + row.get("ID") + "，--Name--" + row.get("name"));
//        }
//    }

    /**
     * 创建表并写入数据
     *
     * @throws IOException
     */
//    public static void createTable() throws IOException {
//        Database db = DatabaseBuilder.open(new File(FILE_PATH));
//        Table newTable;
//        try {
//            newTable = new TableBuilder("NewTable")
//                    .addColumn(new ColumnBuilder("id")
//                            .setSQLType(Types.INTEGER))
//                    .addColumn(new ColumnBuilder("name")
//                            .setSQLType(Types.VARCHAR))
//                    .toTable(db);
//            newTable.addRow(1, "foo");
//            System.out.println("创建成功");
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    /**
     * 删除一条数据
     *
     * @throws IOException
     */
//    public static void deleteRec() throws IOException {
//        Table table = DatabaseBuilder.open(new File(FILE_PATH)).getTable("NewTable22");
//        Row row = CursorBuilder.findRow(table, Collections.singletonMap("xh", "4"));
//        if (row != null) {
//            System.out.println("Found row where 'a' == 'foo': " + row);
//            table.deleteRow(row);
//        } else {
//            System.out.println("Could not find row where 'a' == 'foo'");
//        }
//    }
}
