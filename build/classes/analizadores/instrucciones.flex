//------------------>1era area<--------------------------    

package backend.analizadorInstrucciones;
import java_cup.runtime.*;
import static backend.analizadorInstrucciones.sym.*;
import frontend.gui.FramePrincipal;


%% //------------------>2da area<--------------------------    

%public
%class AnalizadorInstrucciones
%cup
%cupdebug
%unicode
%line
%column
%ignorecase


Salto = \r|\n|\r\n
Espacio = {Salto} | [ \t\f]
Identificador = [:]([:jletterdigit:] | [-] | [_] | [$] )+
Digitos= 0|[1-9][:digit:]*

%{
  FramePrincipal miFrame;

  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }

  public void iniciarFrame(FramePrincipal miFrame){
    this.miFrame=miFrame;
  }
  

%}


%% //------------------>3er area<--------------------------    
 <YYINITIAL>  {

    {Identificador} {return symbol(IDENTIFICADOR,yytext());}

    {Digitos}	{return symbol(NUMERO,yytext());}				
	
    "="    {return symbol(IGUAL,yytext());}

    "+"    {return symbol(MAS,yytext());}

    "-"    {return symbol(MENOS,yytext());}

    "*"    {return symbol(POR,yytext());}

    "/"    {return symbol(DIV,yytext());}

   "("    {return symbol(PARENTESIS_ABIERTO,yytext());}

   ")"    {return symbol(PARENTESIS_CERRADO,yytext());}

   "forward" {return symbol(FORWARD,yytext());}

   "fd" {return symbol(FD,yytext());}

   "Backward" {return symbol(BACKWARD,yytext());}

   "bk" {return symbol(BK,yytext());}

   "Right" {return symbol(RIGHT,yytext());}

   "rt" {return symbol(RT,yytext());}

   "Left" {return symbol(LEFT,yytext());}

   "lt" {return symbol(LT,yytext());}

   "Clears" {return symbol(CLEARS,yytext());}

   "cs" {return symbol(CS,yytext());}

   "PenUp" {return symbol(PEN_UP,yytext());}

   "pu" {return symbol(PU,yytext());}

   "PenDown" {return symbol(PEN_DOWN,yytext());}

   "pd" {return symbol(PD,yytext());}

   "ToCenter" {return symbol(TO_CENTER,yytext());}

   "CR" {return symbol(CR,yytext());}
    
   "Color" {return symbol(COLOR,yytext());}

   "[#"([a-fA-F]|[0-9]){6}"]"      {return symbol(CODIGO_COLOR,yytext());}  

   "PositionXY" {return symbol(POSITION_XY,yytext());}

   "posxy" {return symbol(POS_XY,yytext());}

   "PositionX" {return symbol(POSITION_X,yytext());}

   "posx" {return symbol(POS_X,yytext());}

   "PositionY" {return symbol(POSITION_Y,yytext());}

   "posy" {return symbol(POS_Y,yytext());}

   "HideTurtle" {return symbol(HIDE_TURTLE,yytext());}

   "ht" {return symbol(HT,yytext());}

  "ShowTurtle" {return symbol(SHOW_TURTLE,yytext());}

   "st" {return symbol(ST,yytext());}

  "ToErase" {return symbol(TO_ERASE,yytext());}

   "te" {return symbol(TE,yytext());}

  "ToDraw" {return symbol(TO_DRAW,yytext());}

   "td" {return symbol(TD,yytext());}

   "Repeat" {return symbol(REPEAT,yytext());}

   "," {return symbol(COMA,yytext());}

    "]" {return symbol(CORCHETE_ABIERTO,yytext());}

    "[" {return symbol(CORCHETE_CERRADO,yytext());}

   "#"~"\n" {System.out.println("COMENTARIO:"+yytext());return symbol(COMENTARIO,yytext());}


{Espacio} 	{/*IGNORAMOS*/}
}

//<<EOF>>                 { return symbol(EOF);

[^]     { miFrame.escribirMensaje("Error lexico no entiendo el token:"+yytext()+"\n");return symbol(ERROR_LEXICO);}
