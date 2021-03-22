package xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ʹ��Dom����XML�ĵ�
 */
public class ParseXmlDemo {
    public static void main(String[] args) {

        /**
         * ��emplist.xml�ļ��е�����Ա����Ϣ��ȡ����
         *
         */
        List<Emp> empList =new ArrayList<>();
        /**
         * ʹ��dom4j����xml���²���
         * 1 ����saxreader
         * 2 ʹ������reader ָ����xml�ĵ�������
         * document����
         * 3 ͨ��document �����ȡ��Ԫ��
         * 4 ����Ԫ�ؿ�ʼ׷����ȥ��Ԫ���Դﵽ����xml�ĵ����ݵ�Ŀ��
         */

        try {
            SAXReader reader=new SAXReader();
            Document doc= reader.read(new File("./jsdpj_XML/emplish.xml"));
            /**
             * document �ṩ�˻�ȡ��Ԫ�صķ�����
             * element getrootelement������
             *
             * elementÿ��ʵ�����ڱ�ʾxml�ĵ��е�һ��Ԫ�أ�һ�Ա�ǩ��
             *
             * ���÷���
             * string getname������
             * ��ȡ��ǰ��ǩ������
             *
             * string gettext������
             * ��ȡ�м�� �ı���Ϣ
             *
             * element element��string name��
             *��ȡ��ǰ��ǩ��ָ�����ֵ��ӱ�ǩ
             *
             * list elements����
             * ��ȡ��ǰ��ǩ�������ӱ�ǩ�����ص�list�����л�������ɵ�
             * element ʵ����ÿ��ʵ��������һ���ӱ�ǩ
             *
             * list elements��string name��
             * ��ȡ��ǰ��ǩ������ͬ���ֱ�ǩ��ָ�����֣�
             */
            Element element= doc.getRootElement();
            /**
             * ���������ӱ�ǩ������
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

                //��ȡid
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
