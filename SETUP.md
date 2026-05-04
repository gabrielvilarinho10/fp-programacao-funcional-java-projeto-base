# Guia de Setup — Programação Funcional em Java 25

> **Pré-requisito:** JDK 25 ou superior instalado.
> Baixe em: https://adoptium.net ou https://www.oracle.com/java/technologies/downloads/

---

## Verificar o Java instalado

```bash
java -version
```
A saída deve mostrar `java version "25"` ou superior.

---

## ▶ Terminal (Windows / Linux / macOS)

### Compilar
```bash
# Windows
mvnw.cmd compile

# Linux / macOS
./mvnw compile
```

### Rodar os testes
```bash
# Windows
mvnw.cmd test

# Linux / macOS
./mvnw test
```

### Executar uma classe específica
```bash
# Windows
mvnw.cmd exec:java -Dexec.mainClass="exercicios.Aula04"
mvnw.cmd exec:java -Dexec.mainClass="exercicios.Aula06"

# Linux / macOS
./mvnw exec:java -Dexec.mainClass="exercicios.Aula04"
./mvnw exec:java -Dexec.mainClass="exercicios.Aula06"
```

---

## 💡 IntelliJ IDEA

1. **File → Open** → selecione a pasta raiz do projeto (onde está o `pom.xml`)
2. O IntelliJ detecta automaticamente o Maven e importa o projeto
3. Aguarde a indexação e o download das dependências
4. Para rodar os testes: clique com o botão direito no projeto → **Run All Tests**
5. Para executar uma aula: abra `Aula04.java` ou `Aula06.java` → clique no ícone ▶ ao lado de `main()`

---

## 💙 VS Code

1. Instale o **Extension Pack for Java** (ID: `vscjava.vscode-java-pack`) — o arquivo `.vscode/extensions.json` já sugere automaticamente
2. **File → Open Folder** → selecione a pasta raiz do projeto
3. Aguarde o VS Code detectar o Maven e baixar as dependências
4. Para rodar uma aula: use o menu **Run → Start Debugging** (as configurações já estão em `.vscode/launch.json`)
5. Para rodar os testes: abra `Aula04Test.java` ou `Aula06Test.java` → clique em **Run Test** acima de cada método

---

## 🌑 Eclipse

1. **File → Import → Maven → Existing Maven Projects**
2. Selecione a pasta raiz do projeto → **Finish**
3. O Eclipse (com plugin m2e) baixa as dependências automaticamente
4. Para rodar os testes: clique com o botão direito no projeto → **Run As → JUnit Test**
5. Para executar uma aula: clique com o botão direito em `Aula04.java` → **Run As → Java Application**

---

## 🏁 NetBeans

1. **File → Open Project** → selecione a pasta raiz do projeto
2. O NetBeans detecta o `pom.xml` automaticamente
3. Clique com o botão direito no projeto → **Test** para rodar os testes
4. Para executar: clique com o botão direito em `Aula04.java` → **Run File**

---

## 📦 Estrutura do Projeto

```
src/
├── main/java/exercicios/
│   ├── base/Aula.java               # Classe base dos exercícios
│   ├── AppProgramacaoFuncional.java # Sandbox para experimentos livres
│   ├── Aula04.java                  # ← Implemente os exercícios aqui
│   ├── Aula06.java                  # ← Implemente os exercícios aqui
│   ├── Campus.java
│   ├── Cidade.java
│   ├── Curso.java
│   ├── Estado.java
│   ├── Estudante.java
│   └── StudentGenerator.java        # Gerador de dados de teste
└── test/java/exercicios/
    ├── Aula04Test.java              # Testes automáticos da Aula 04
    └── Aula06Test.java              # Testes automáticos da Aula 06
```

---

## ✅ Verificar se tudo funciona

```bash
# Windows
mvnw.cmd test

# Linux / macOS
./mvnw test
```

Todos os testes devem passar (`BUILD SUCCESS`).
