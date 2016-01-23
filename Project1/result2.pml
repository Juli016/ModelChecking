bool send_flag = False;
bool receive_flag = False;
int send_content = 0;
int receive_content = 0;
mtype mail_state;
mtype send_auth;
int sentmail;
int sentmail_temp=0;
mtype receive_auth;
int recvmail;
int recvmail_temp=0;
mtype gate_state;
mtype open_auth;
mtype close_auth;
chan send = [0] of {byte,int}
chan receive = [0] of {byte,int}
chan open = [0] of {byte}
chan close = [0] of {byte}
mtype={mail_S1,gate_Closed,gate_Open,send_env,send_trigger,receive_env,receive_trigger,open_env,open_trigger,close_env,close_trigger};

active proctype mail()
{
beginning:
S1:
atomic{
mail_state=mail_S1;
if
::send?1(sentmail)->atomic{
send_flag=true;
goto S1;
}
::receive?1(recvmail)->atomic{
receive_flag=true;
goto S1;
}
fi
}
}

active proctype gate()
{
beginning:
Closed:
atomic{
gate_state=gate_Closed;
open?1->atomic{
goto Open;
}
}
Open:
atomic{
gate_state=gate_Open;
close?1->atomic{
goto Closed;
}
}
}

active proctype IFTTT1()
{
beginning:
S1:
atomic{
receive_flag==true->atomic{
goto S2;
}
}
S2:
atomic{
send_auth=send_trigger
send!1(recvmail);
goto S1;
}
}

active proctype IFTTT2()
{
beginning:
S1:
atomic{
((send_flag==true) && (sentmail==10))->atomic{
goto S2;
}
}
S2:
atomic{
open_auth=open_trigger
open!1;
goto S1;
}
}

active proctype env()
{
S1:
atomic{
if
::atomic{
send_auth=send_env
do
::sentmail_temp<100->sentmail_temp++
::break
od
send!1(sentmail_temp);
}
::atomic{
receive_auth=receive_env
do
::recvmail_temp<100->recvmail_temp++
::break
od
receive!1(recvmail_temp);
}
fi
goto S1;
}
}
