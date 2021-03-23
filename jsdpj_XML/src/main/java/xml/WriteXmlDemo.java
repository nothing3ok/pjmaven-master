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
 * ʹ��dom4j����һ��xml�ĵ�
 */
public class WriteXmlDemo {
    public static void main(String[] args) {
        List<Emp> emps=new ArrayList<>();
        emps.add(new Emp(1,"����",18,"��",300));

        emps.add(new Emp(2,"��",18,"��",300));

        emps.add(new Emp(3,"��",18,"��",300));

        emps.add(new Emp(4,"����",18,"��",300));

        /**
         * д��һ��xml�ĵ��Ĵ��²���
         * 1������һ��document ���󡣱�ʾһ���հ��ĵ�
         * 2 ��document ��������Ӹ�Ԫ��
         * 3 ���Ԫ�����������Ԫ�أ����γ�xml�ĵ�Ӧ�е���״�ṹ
         * 4 ����xml writer
         * 5 ͨ��xmlwriter��document ����д�����γ�xml �ĵ�
         * 6 ��xmlwriter �ر�
         */
        try {
            Document doc=DocumentHelper.createDocument();
            /**
             * document �ṩ�ķ�����
             * element addelement��string name��
             * ��ǰ�ĵ������ָ�����ֵĸ�Ԫ�أ�������һ��element ʵ����ʽ���أ�
             * �Ա�����Ըø�Ԫ������������
             * ע�⣬�÷���ֻ�ܵ���һ�Σ���Ϊһ���ĵ��У�ֻ����һ����
             */
            Element root = doc.addElement("list");
            for (Emp emp : emps) {
                //���Ԫ����׷��һ��EMP  ��ǩ
                Element empEle = root.addElement("emp");
                //1  ��emp ��ǩ��׷��һ��name �ӱ�ǩ
                Element name = empEle.addElement("name");
                //2 �� name �ӱ�ǩ��׷��һ���ı���Ϣ
                 name.addText(emp.getName());
                 //+age
                Element ageEle=empEle.addElement("age");
                ageEle.addText(emp.getAge()+"");
                //+gender
                empEle.addElement("gender").addText(emp.getName());
                //whish
                empEle.addElement("with").addText(emp.getWith()+"");
                //+id ����
                empEle.addAttribute("id",emp.getId()+"");
            }

            XMLWriter writer=new XMLWriter(
                    new FileOutputStream("xyemp.xml"),OutputFormat.createPrettyPrint());
            /**
             * ��document ����ͨ��xmlwriter д�����γ�xml �ĵ�
             *
             */
            writer.write(doc);
            System.out.println("���");
            writer.close();




        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
