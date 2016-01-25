package modelChecking;

class Rule {
	int srcFur;				// if����Ҿߵı��
	String srcVar;			// ��һ���Ҿ�ĳ����������
	String condition;		// ��һ���Ҿ����������
	
	int dstFur;				// that����ļҾ߱��
	String dstAction;		// �ڶ����Ҿ�ִ�е�action����
}

class Spec {
	String spec;
}

class Variable
{
	String varName;		// ��������
	int varType;		// �������ͣ�(0Ϊint�ͣ�1Ϊenum�ͣ�2Ϊboolean��)
	String rage;		// ������Χ

	String initVal;		// ��ʼ��ֵ����û�г�ʼ��ֵΪ���ַ���
	String curVal;		// ��ǰȡֵ
}

class TransVar			// ��action�иı�variable��ֵ
{
	String val;			// Ҫ�ı�ļҾ߱�����variArr�е�����
	String valRst;		// �ı���ֵ
}

class Action
{
	String actionName;			// action���� 
	String[] startState;		// ��ʼ��State
	String endState;			// ������State
	TransVar[] trans;			// ִ�е�transitions
}

class Furniture
{
	String furname;					// �Ҿ�����
	String curState;				// �Ҿߵ�ǰ״̬���
	String initState;				// �Ҿ߳�ʼ״̬����û��Ϊ���ַ���

	String[] StateArr;				// ���е�״̬�б�
	Variable[] variArr;				// ���е��ڲ�����
	Action[] actionArr;				// ����ִ�е�action�б�
	Rule[] internRules;				// �ڲ�����
}

public class Common {
	public native boolean Check(Rule[] R_Array, Spec[] S_Array, Furniture[] F_Array);
	private static Rule[] R_Array;
	private static Spec[] S_Array;
	private static Furniture[] F_Array;

	static {
		System.loadLibrary("Check");
	}

	public static void main(String[] args) {
		new Common().Check(R_Array, S_Array, F_Array);
	}	
}
