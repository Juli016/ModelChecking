#pragma once

#include<string>
#include<vector>

using std::vector;
using std::string;

typedef int FurIndex;		// �Ҿ߱��
typedef int VarIndex;		// �Ҿ��ڲ��������
typedef int ActIndex;		// �Ҿ߿�ִ�ж���

// enum VarType {Boolean, Int ,Enum};			// smv�������ֱ���������

// �û������rule
struct Rule
{
	FurIndex srcFur;		// if����Ҿߵı��
	VarIndex srcVar;		// ��һ���Ҿ�ִ�е�action���
	string condition;		// ��һ���Ҿ����������

	FurIndex dstFur;		// else����ļҾ߱��
	ActIndex dstEvent;		// �ڶ����Ҿ�ִ�е�action���
};

// �Ҿ�fsm�е�State
struct State
{
	bool init;				// �Ƿ��г�ʼ��ֵ
	string initVal;			// ��ʼ��ֵ
	string StateName;		// State����
};

// �û�����Ĺ�Լ
struct Spec
{
	string spec;
};

// ÿ���Ҿߵ��ڲ�����
struct Variable
{
	string varName;		// ��������
	string varType;		// ��������

	bool init;			// �Ƿ��ʼ��
	string initVal;		// ��ʼ��ֵ

	string curState;	// ��ǰȡֵ

	// ����ֱ�Ӷ�string
/*	union valRange
	{
		// boolean�ͱ���
		struct				
		{
			bool cur;			// ��ǰ��ֵ
		};

		// int�ͱ���
		struct
		{
			int lowBound;		// int���½�
			int highBound;		// int���Ͻ�
			int cur;
		};

		// enum�ͱ���
		struct
		{
			int valueNum;		// ������ö��ֵ
			string *name;		// ö��ֵ������
			int cur;
		};
	};
	*/
};

// ��action�иı�Ҿ��ڲ�������ֵ
struct TransVar			// ��action�иı�variable��ֵ
{
	VarIndex val;
	string valRst;
};

// �Ҿ߿���ʹ�õ�action
struct Action
{
	string actionName;			// action���� 
	string startState;			// ��ʼ��State
	string endState;			// ������State
	vector<TransVar> trans;		// ִ�е�transitions
};

// ÿ���ҾߵĶ���
struct Furniture
{
	string furname;					// �Ҿ�����
	State curState;					// ��¼�Ҿߵ�ǰ״̬

	vector<State> StateVec;			// ���е�״̬�б�
	vector<Variable> variVec;		// ���е��ڲ�����
	vector<Action> actionVec;		// ����ִ�е�action�б�
	vector<Rule> internRules;		// �ڲ�����
};

// �Զ����Ķ���
struct FSM				
{
	Furniture *f;					// ÿ���Ҿ�����һ��fsm��fָ��Ҿ�����
	vector<Rule> externRules;		// ���е�rules
};