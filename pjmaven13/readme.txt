���汾����û��������

����ע������̲�������û�����

ʵ�֣�
1 ��webapps/myweb Ŀ¼���½�3��ҳ��
login.html ����ҳ��
�����ҳ���ж���һ�� form �� action ָ����./login��
�����ڱ��� �ж������������ֱ����������û����Լ����룬
������������name ���Էֱ�Ϊ��username��password
login����success.html ����ɹ���ʾҳ��
login����fail.html ����ʧ����ʾҳ��

2 ��com.webserver.servlet ���ж������ڴ������ҵ����ࣺLoginServlet ������service����

3 ��clienthandler �����벿�����һ���µķ�֧��������·���ǡ�/myweb/login����ʵ����loginservlet
��������service �����������ҵ��

loginservlet �ĵ���ҵ���������
1 ����ͨ��request ��ȡ�û��ڱ���������û����Լ�����
2 ʹ��randomaccessfile ��ȡ user��dat�ļ�
3 ˳���ȡÿ����¼�����ȶ��û��������룬���бȶԳɹ��ģ�������response ��Ӧ����ɹ�ҳ��
���û�����ȷ�����벻��ȷ��user��dat�ļ���û�и��û���������response��Ӧ����ʧ��ҳ��
