# Trabalho Pratico de Sistemas Distribuidos

Amadeu Marques up201804974
Eduardo Santos up201805449

## Exercicio 1

Mudar para o diretorio do exercicio
```
cd ex1/src/
```

Compilar
```
javac ds/trabalho/parte1/Peer.java
```

Executar
```
java ds.trabalho.parte1.Peer <source_machine> <dest_machine>
```

Para iniciar o envio dos tokens é necessário usar o unlock() em uma das shells.

Para parar temporariamente o fluxo usamos lock().

Para repor o fluxo usamos novamente unlock().

## Exercicio 2

Mudar para o diretorio do exercicio
```
cd ex2/src/
```

Compilar
```
./gradlew build
./gradlew installDist
```

Executar
```
./build/install/src/bin/peer-serv <source_machine>
```

Para registar uma maquina é usado register(<maquina>)

Depois de registada uma máquina podem ser feitos os comandos push(<maquina>), pull(<maquina>) e pushpull(<maquina>), caso a maquina não esteja registada, não será possível fazer estes comandos.

O dicionário é atualizado com três novas entradas a cada register que é feito com o seu nome.


## Exercicio 3

Mudar para o diretorio do exercicio
```
cd ex3/src/
```
Compilar
```
javac ds/trabalho/parte3/Peer.java
```
Executar
```
java ds.trabalho.parte3.Peer <source_machine> <other_machine1> <other_machine2> <other_machine3>
```

Para enviar mensagens basta escrever no terminal.

## Extras
Durante a construção do projeto tivemos bastante dificuldade em aceder as maquinas. Então usamos o docker para simular a rede e as maquinas.

Para correr as maquinas em docker basta consultar o ficheiro comandos-docker.txt no interior de cada diretorio de exercicio.
