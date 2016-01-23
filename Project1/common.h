#pragma once

#include<string>
#include<vector>

using std::vector;
using std::string;

typedef int FurIndex;		// 家具编号
typedef int VarIndex;		// 家具内部变量编号
typedef int ActIndex;		// 家具可执行动作

// enum VarType {Boolean, Int ,Enum};			// smv中有三种变量的类型

// 用户输入的rule
struct Rule
{
	FurIndex srcFur;		// if语句后家具的编号
	VarIndex srcVar;		// 第一个家具执行的action编号
	string condition;		// 第一个家具满足的条件

	FurIndex dstFur;		// else语句后的家具编号
	ActIndex dstEvent;		// 第二个家具执行的action编号
};

// 家具fsm中的State
struct State
{
	bool init;				// 是否有初始化值
	string initVal;			// 初始化值
	string StateName;		// State名称
};

// 用户输入的规约
struct Spec
{
	string spec;
};

// 每个家具的内部变量
struct Variable
{
	string varName;		// 变量名称
	string varType;		// 变量类型

	bool init;			// 是否初始化
	string initVal;		// 初始化值

	string curState;	// 当前取值

	// 不如直接读string
/*	union valRange
	{
		// boolean型变量
		struct				
		{
			bool cur;			// 当前的值
		};

		// int型变量
		struct
		{
			int lowBound;		// int型下界
			int highBound;		// int型上界
			int cur;
		};

		// enum型变量
		struct
		{
			int valueNum;		// 共几个枚举值
			string *name;		// 枚举值的名字
			int cur;
		};
	};
	*/
};

// 在action中改变家具内部变量的值
struct TransVar			// 在action中改变variable的值
{
	VarIndex val;
	string valRst;
};

// 家具可以使用的action
struct Action
{
	string actionName;			// action名称 
	string startState;			// 起始的State
	string endState;			// 结束的State
	vector<TransVar> trans;		// 执行的transitions
};

// 每个家具的定义
struct Furniture
{
	string furname;					// 家具名称
	State curState;					// 记录家具当前状态

	vector<State> StateVec;			// 所有的状态列表
	vector<Variable> variVec;		// 所有的内部变量
	vector<Action> actionVec;		// 可以执行的action列表
	vector<Rule> internRules;		// 内部规则
};

// 自动机的定义
struct FSM				
{
	Furniture *f;					// 每个家具生成一个fsm，f指向家具数组
	vector<Rule> externRules;		// 所有的rules
};