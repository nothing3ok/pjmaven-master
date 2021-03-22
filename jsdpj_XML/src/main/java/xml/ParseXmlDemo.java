package xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用Dom解析XML文档
 */
public class ParseXmlDemo {
    public static void main(String[] args) {

        /**
         * 将emplist.xml文件中的所有员工信息读取出来
         *
         */
        List<Emp> empList =new ArrayList<>();
        /**
         * 使用dom4j解析xml大致步骤
         * 1 创建saxreader
         * 2 使用洒下reader 指定的xml文档并生成
         * document对象
         * 3 通过document 对象获取根元素
         * 4 跟从元素开始追级过去子元素以达到遍历xml文档数据的目的
         */

        try {
            SAXReader reader=new SAXReader();
            Document doc= reader.read(new File("./jsdpj_XML/emplish.xml"));
            /**
             * document 提供了获取跟元素的方法：
             * element getrootelement（）；
             *
             * element每个实例用于表示xml文档中的一个元素（一对标签）
             *
             * 常用方法
             * string getname（）；
             * 获取当前标签的名字
             *
             * string gettext（）；
             * 获取中间的 文本信息
             *
             * element element（string name）
             *获取当前标签中指定名字的子标签
             *
             * list elements（）
             * 获取当前标签中所有子标签，返回的list集合中会包含若干的
             * element 实例，每个实例是其中一个子标签
             *
             * list elements（string name）
             * 获取当前标签中所有同名字标签（指定名字）
             */
            Element element= doc.getRootElement();
            /**
             * 里面可以添加标签的名字
             */
            List<Element> f=element.elements("emp");

            for (Element empEle : f) {
                Element nameEle=empEle.element("name");
                String name1=nameEle.getText();
                System.out.println(name1);
                Element ageEel=empEle.element("age");
                int age= Integer.parseInt(ageEel.getText());
                System.out.println(age);


                String gender=empEle.elementText("gender");
                System.out.println(gender);
                int with =Integer.parseInt(empEle.elementText("with"));
                System.out.println(with);

                //获取id
//                Attribute attr=empEle.attribute("id");
//                int id=Integer.parseInt(attr.getValue());
                //int id =Integer.parseInt(empEle.attribute("id").getValue());
                int id =Integer.parseInt(empEle.attributeValue("id"));
                Emp emp3=new Emp(id,name1,age,gender,with);
                empList.add(emp3);
                for (Emp emp1 : empList) {
                    System.out.println(emp1);
                }
            }











        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
