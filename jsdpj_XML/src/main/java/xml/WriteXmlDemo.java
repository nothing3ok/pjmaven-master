package xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用dom4j生成一个xml文档
 */
public class WriteXmlDemo {
    public static void main(String[] args) {
        List<Emp> emps=new ArrayList<>();
        emps.add(new Emp(1,"张三",18,"男",300));

        emps.add(new Emp(2,"发",18,"男",300));

        emps.add(new Emp(3,"三",18,"男",300));

        emps.add(new Emp(4,"三的",18,"男",300));

        /**
         * 写出一个xml文档的大致步骤
         * 1：创建一个document 对象。表示一个空白文档
         * 2 向document 对象中添加根元素
         * 3 向根元素中逐级添加子元素，以形成xml文档应有的树状结构
         * 4 创建xml writer
         * 5 通过xmlwriter将document 对象写出以形成xml 文档
         * 6 将xmlwriter 关闭
         */
        try {
            Document doc=DocumentHelper.createDocument();
            /**
             * document 提供的方法：
             * element addelement（string name）
             * 向当前文档中添加指定名字的根元素，并将其一个element 实例形式返回，
             * 以便继续对该根元素做后续操作
             * 注意，该方法只能调用一次，因为一个文档中，只能有一个根
             */
            Element root = doc.addElement("list");
            for (Emp emp : emps) {
                //向根元素中追加一个EMP  标签
                Element empEle = root.addElement("emp");
                //1  向emp 标签中追加一个name 子标签
                Element name = empEle.addElement("name");
                //2 向 name 子标签中追加一个文本信息
                 name.addText(emp.getName());
                 //+age
                Element ageEle=empEle.addElement("age");
                ageEle.addText(emp.getAge()+"");
                //+gender
                empEle.addElement("gender").addText(emp.getName());
                //whish
                empEle.addElement("with").addText(emp.getWith()+"");
                //+id 属性
                empEle.addAttribute("id",emp.getId()+"");
            }

            XMLWriter writer=new XMLWriter(
                    new FileOutputStream("xyemp.xml"),OutputFormat.createPrettyPrint());
            /**
             * 将document 对象通过xmlwriter 写出以形成xml 文档
             *
             */
            writer.write(doc);
            System.out.println("完成");
            writer.close();




        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
