class Rule {
	int srcFur;			// if����Ҿߵı��
	int srcVar;			// ��һ���Ҿ�ִ�е�action���
	String condition;   // ��һ���Ҿ����������
	
	int dstFur;			// that����ļҾ߱��
	int dstEvent;		// �ڶ����Ҿ�ִ�е�action���
}

class Spec {
	String spec;
}

class State
{
	boolean init;			// �Ƿ��г�ʼ��ֵ
	String initVal;			// ��ʼ��ֵ
	String StateName;		// State����
};

class Variable
{
	String varName;		// ��������
	int varType;		// ��������
	String rage;		// ������Χ

	boolean init;		// �Ƿ��ʼ��
	String initVal;		// ��ʼ��ֵ

	String curState;	// ��ǰȡֵ
}

class TransVar			// ��action�иı�variable��ֵ
{
	int val;
	String valRst;
}

class Action
{
	String actionName;			// action���� 
	String startState;			// ��ʼ��State
	String endState;			// ������State
	TransVar[] trans;			// ִ�е�transitions
}

class Furniture
{
	String furname;					// �Ҿ�����
	State curState;					// ��¼�Ҿߵ�ǰ״̬

	State[] StateVec;			// ���е�״̬�б�
	Variable[] variVec;		// ���е��ڲ�����
	Action[] actionVec;		// ����ִ�е�action�б�
	Rule[] internRules;		// �ڲ�����
}

public class common {
	public��native��boolean Check(Rule[] R_Array,Spec[] S_Array,String JsonName);
	private static Rule[] R_Array;
	private static Spec[] S_Array;
	private static String JsonName;
	static {
		System.loadLibrary("Check");
	}
	public static void main(String[] args) {
		new common().Check(R_Array,S_Array,JsonName);
	}	
}