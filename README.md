# webdev2024-2

## Sobre o projeto
Este projeto é uma aplicação web Java com foco em operações de leilão/listagem de produtos, construída para fins acadêmicos na disciplina de desenvolvimento web.

O intuito principal é demonstrar a integração entre **camada de apresentação**, **regras de negócio** e **persistência de dados**, aplicando boas práticas como arquitetura **MVC** e o padrão de projeto **DAO**.

## Como funciona
O sistema segue um fluxo tradicional de aplicações Java Web:

- O usuário acessa as páginas pela interface web.
- As requisições são tratadas por **Servlets** (camada de controle).
- As Servlets acionam os modelos e os objetos de acesso a dados.
- As respostas são renderizadas em páginas **JSP (JavaServer Pages)**.
- Algumas interações podem ser atualizadas de forma assíncrona via **AJAX**, sem recarregar toda a página.

## Arquitetura e padrões utilizados

### MVC
- **Model**: classes de domínio como `Produto`, `Usuario` e `Lance`.
- **View**: páginas JSP, HTML, CSS e scripts JavaScript.
- **Controller**: Servlets responsáveis por receber requisições, validar dados e encaminhar respostas.

### DAO (Data Access Object)
O padrão DAO é usado para isolar o acesso ao banco de dados em classes específicas (por exemplo, `ProdutoDAO`, `UsuarioDAO`, `LanceDAO`).

Vantagens dessa abordagem:
- separação de responsabilidades;
- organização e manutenção mais simples;
- facilidade para evoluir a camada de persistência sem impactar a interface.

## Tecnologias utilizadas
- **Java** (lógica de negócio e backend);
- **Servlets** (controle das requisições HTTP);
- **JSP - JavaServer Pages** (renderização dinâmica no servidor);
- **JavaScript** (comportamento no cliente);
- **AJAX** (requisições assíncronas para melhor experiência do usuário);
- **HTML** (estrutura da interface);
- **CSS** (estilização da interface);
- **Banco de dados relacional** (persistência de usuários, produtos e lances);
- **Apache Maven** (gerenciamento de build e dependências).

## Estrutura principal do projeto
- `src/main/java/model`: entidades do sistema.
- `src/main/java/controller`: Servlets/controllers.
- `src/main/java/database`: DAOs e acesso ao banco.
- `src/main/webapp`: páginas JSP, arquivos estáticos e configuração web.

## Objetivo educacional
Além de entregar funcionalidades web, o projeto serve como prática de:

- desenvolvimento full stack em Java Web;
- organização de código em camadas;
- comunicação cliente-servidor com AJAX;
- uso de padrões de projeto em cenários reais de CRUD e listagens.
