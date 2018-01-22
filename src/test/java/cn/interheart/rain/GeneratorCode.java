package cn.interheart.rain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

/**
 * @author Administrator
 * @date 2018/01/20 14:15
 */
public class GeneratorCode {
    // 指定实体生成所在包的路径
    private String packageOutPath = "com.scms.entity";
    // 作者名字
    private String authorName = "wang";
    // 数据库表名
    private String tablename = "student";
    // 列名数组
    private String[] colnames;
    // 列名类型数组
    private String[] colTypes;
    // 列名大小数组
    private int[] colSizes;
    // 是否需要导入包java.util.*
    private boolean f_util = false;
    // 是否需要导入包java.sql.*
    private boolean f_sql = false;

    // 数据库连接
    private static final String URL = "jdbc:mysql://192.168.0.181:3306/smart_school";
    private static final String NAME = "root";
    private static final String PASS = "123456";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    /*
     * 构造函数
     */
    public GeneratorCode() {
        // 创建连接
        Connection con = null;
        // 查要生成实体类的表
        String sql = "select * from " + tablename;
        Statement pStemt = null;
        try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            con = DriverManager.getConnection(URL, NAME, PASS);
            pStemt = (Statement) con.createStatement();
            ResultSet rs = pStemt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int size = rsmd.getColumnCount(); // 统计列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);

                if (colTypes[i].equalsIgnoreCase("date")
                        || colTypes[i].equalsIgnoreCase("timestamp")) {
                    f_util = true;
                }
//                if (colTypes[i].equalsIgnoreCase("blob")
//                        || colTypes[i].equalsIgnoreCase("char")) {
//                    f_sql = true;
//                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }

            String content = parse(colnames, colTypes, colSizes);

            try {
                String path = this.getClass().getResource("").getPath();

                System.out.println(path);
                System.out.println("src/?/"
                        + path.substring(path.lastIndexOf("/cn/",
                        path.length())));
                // String outputPath = directory.getAbsolutePath()+
                // "/src/"+path.substring(path.lastIndexOf("/com/",
                // path.length()), path.length()) + initcap(tablename) +
                // ".java";
                String outputPath = "/src/"
                        + this.packageOutPath.replace(".", "/");
                File file = new File(outputPath);
                file.mkdirs();
                File file1 = new File(file, initcap(tablename) + ".java");
                FileWriter fw = new FileWriter(file1);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(content);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 功能：生成实体类主体代码
     */
    private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();

        sb.append("package " + this.packageOutPath + ";\r\n");
        sb.append("\r\n");
        // 判断是否导入工具包
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("import lombok.Data;\n" +
                "import lombok.experimental.Accessors;");
        // 注释部分
        sb.append("\r\n/**\r\n");
        sb.append("* " + tablename + " 实体类\r\n");
        sb.append("* " + new Date() + " " + this.authorName + "\r\n");
        sb.append("*/ \r\n");
        sb.append("@Data" + "\r\n");
        sb.append("@Accessors(chain = true)" + "\r\n");
        // 实体部分
        sb.append("public class " + initcap(tablename) + "{\r\n");
        processAllAttrs(sb);// 属性
//        processAllMethod(sb);// get set方法
        sb.append("}\r\n");

        // System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     */
    private void processAllAttrs(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " "
                    + columToJave(colnames[i]) + ";\r\n");
        }

    }

    /**
     * 数据字段变成Java属性
     */
    private String columToJave(String string) {
        String string2 = string.toLowerCase();
        StringBuilder builder = new StringBuilder(string2);

        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i) == '_') {
                // 第一次出现该符号的位置
                char c = builder.charAt(i + 1);
                c = (char) (c - 32);
                StringBuilder replace1 = builder.replace(i + 1, i + 2, c + "");
                builder = replace1.replace(i, i + 1, "");

                // 最后一次出现该符号的位置
                int of = builder.lastIndexOf("_", string2.length());
                if (of != -1) {
                    char c1 = builder.charAt(of + 1);
                    c1 = (char) (c1 - 32);
                    StringBuilder replace2 = builder.replace(of + 1, of + 2, c1
                            + "");
                    builder = replace2.replace(of, of + 1, "");
                    ;
                }

            }
        }
        return builder.toString();
    }

    /**
     * 功能：生成所有方法
     */
    private void processAllMethod(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initcaps(columToJave(colnames[i]))
                    + "(" + sqlType2JavaType(colTypes[i]) + " "
                    + columToJave(colnames[i]) + "){\r\n");
            sb.append("\t\tthis." + columToJave(colnames[i]) + "="
                    + columToJave(colnames[i]) + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get"
                    + initcaps(columToJave(colnames[i])) + "(){\r\n");
            sb.append("\t\treturn " + columToJave(colnames[i]) + ";\r\n");
            sb.append("\t}\r\n");
        }

    }

    /**
     * 功能：将输入字符串的首字母改成大写
     */
    private String initcap(String string2) {
        String str = columToJave(string2);
        char[] ch = str.toCharArray();
        if (!string2.contains("_")) {
            ch[0] = (char) (ch[0] - 32);
        } else {
            for (int j = 0; j < 3; j++) {
                ch[j] = (char) (ch[j] - 32);
            }
        }
        return new String(ch);
    }

    private String initcaps(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     */
    private String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("binary_double")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("binary_float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("blob")) {
            return "byte[]";
        } else if (sqlType.equalsIgnoreCase("blob")) {
            return "byte[]";
        } else if (sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar2")
                || sqlType.equalsIgnoreCase("varchar2")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("date")
                || sqlType.equalsIgnoreCase("timestamp")
                || sqlType.equalsIgnoreCase("timestamp with local time zone")
                || sqlType.equalsIgnoreCase("timestamp with time zone")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("number")) {
            return "Long";
        }

        return "String";
    }

    /**
     *
     */
    public static void main(String[] args) {

        new GeneratorCode();

    }
}