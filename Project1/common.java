class Rule {
	int srcFur;			// if语句后家具的编号
	int srcVar;			// 第一个家具执行的action编号
	String condition;   // 第一个家具满足的条件
	
	int dstFur;			// that语句后的家具编号
	int dstEvent;		// 第二个家具执行的action编号
}

class Spec {
	String spec;
}

class State
{
	boolean init;			// 是否有初始化值
	String initVal;			// 初始化值
	String StateName;		// State名称
};

class Variable
{
	String varName;		// 变量名称
	int varType;		// 变量类型
	String rage;		// 变量范围

	boolean init;		// 是否初始化
	String initVal;		// 初始化值

	String curState;	// 当前取值
}

class TransVar			// 在action中改变variable的值
{
	int val;
	String valRst;
}

class Action
{
	String actionName;			// action名称 
	String startState;			// 起始的State
	String endState;			// 结束的State
	TransVar[] trans;			// 执行的transitions
}

class Furniture
{
	String furname;					// 家具名称
	State curState;					// 记录家具当前状态

	State[] StateVec;			// 所有的状态列表
	Variable[] variVec;		// 所有的内部变量
	Action[] actionVec;		// 可以执行的action列表
	Rule[] internRules;		// 内部规则
}

public class common {
	public　native　boolean Check(Rule[] R_Array,Spec[] S_Array,String JsonName);
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
