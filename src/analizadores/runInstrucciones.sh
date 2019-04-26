#! /bin/bash 
echo "STARTING JFLEX COMPILING"
java -jar /home/jesfrin/Documentos/LibreriasJava/jflex-1.7.0/lib/jflex-full-1.7.0.jar -d /home/jesfrin/NetBeansProjects/Tortuga2/src/backend/analizadorInstrucciones instrucciones.flex

echo "STARTING CUP COMPILING"
java -jar /home/jesfrin/Documentos/LibreriasJava/cup/java-cup-11b.jar instrucciones.cup 
mv parser.java /home/jesfrin/NetBeansProjects/Tortuga2/src/backend/analizadorInstrucciones/parser.java
mv sym.java /home/jesfrin/NetBeansProjects/Tortuga2/src/backend/analizadorInstrucciones/sym.java


