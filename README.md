# Back-end
Sua tarefa é construir uma API e banco de dados para a aplicação VUTTR (Very Useful Tools to Remember). 

A aplicação é um simples repositório para gerenciar ferramentas com seus respectivos nomes, links, descrições e tags.

A API deverá ser documentada utilizando o formato Swagger.

# O mínimo necessário

* Uma aplicação contendo uma API real simples, sem autenticação, que atenda os requisitos descritos abaixo, fazendo requisições à um banco de dados para persistência;
* README.md contendo informações básicas do projeto e como executá-lo;
* Swagger da aplicação.

# Bônus
Os seguintes itens não são obrigatórios, mas darão mais valor ao seu trabalho
* Uso de ferramentas externas que facilitem o seu trabalho;
* Cuidados especiais com otimização, padrões, entre outros;
* Migrations ou script para configuração do banco de dados utilizado;
* Testes;
* Conteinerização da aplicação;
* Autenticação e autorização (OAuth, JWT);

# Requisitos
1. A API deve responder na porta 3000
2. Deve haver uma rota para listar todas as ferramentas cadastradas

**GET /tools**
```json
[
    {
        id: 1,
        title: "Notion",
        link: "https://notion.so",
        description: "All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized. ",
        tags: [
            "organization",
            "planning",
            "collaboration",
            "writing",
            "calendar"
        ]
    },
    {
        id: 2,
        title: "json-server",
        link: "https://github.com/typicode/json-server",
        description: "Fake REST API based on a json schema. Useful for mocking and creating APIs for front-end devs to consume in coding challenges.",
        tags: [
            "api",
            "json",
            "schema",
            "node",
            "github",
            "rest"
        ]
    },
    {
        id: 3,
        title: "fastify",
        link: "https://www.fastify.io/",
        description: "Extremely fast and simple, low-overhead web framework for NodeJS. Supports HTTP2.",
        tags: [
            "web",
            "framework",
            "node",
            "http2",
            "https",
            "localhost"
        ]
    }
]
```
​
3. Deve ser possível filtrar ferramentas utilizando uma busca por tag

**GET /tools?tag=node   (node é a tag sendo buscada neste exemplo)**
```json
[
    {
        id: 2,
        title: "json-server",
        link: "https://github.com/typicode/json-server",
        description: "Fake REST API based on a json schema. Useful for mocking and creating APIs for front-end devs to consume in coding challenges.",
        tags: [
            "api",
            "json",
            "schema",
            "node",
            "github",
            "rest"
        ]
    },
    {
        id: 3,
        title: "fastify",
        link: "https://www.fastify.io/",
        description: "Extremely fast and simple, low-overhead web framework for NodeJS. Supports HTTP2.",
        tags: [
            "web",
            "framework",
            "node",
            "http2",
            "https",
            "localhost"
        ]
    }
]
```

4. Deve haver uma rota para cadastrar uma nova ferramenta
O corpo da requisição deve conter as informações da ferramenta a ser cadastrada, sem o ID (gerado automaticamente pelo servidor). A resposta, em caso de sucesso, deve ser o mesmo objeto, com seu novo ID gerado.

**POST /tools**

**Content-Type:** application/json
```json
{
    "title": "hotel",
    "link": "https://github.com/typicode/hotel",
    "description": "Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.",
    "tags":["node", "organizing", "webapps", "domain", "developer", "https", "proxy"]
}
```


**Response**

**Status:** 201 Created

**Content-Type:** application/json
```json
{
    "title": "hotel",
    "link": "https://github.com/typicode/hotel",
    "description": "Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.",
    "tags":["node", "organizing", "webapps", "domain", "developer", "https", "proxy"],
    "id":5
}
```

5. O usuário deve poder remover uma ferramenta por ID

**DELETE /tools/:id**

**Status:** 200 OK
```json
{}
```

