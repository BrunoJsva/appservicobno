# appservicobno

# App Serviços BRNO

Sistema Java Web desenvolvido com Spring Boot para gerenciamento de usuários.

## 🚀 Funcionalidades

* Cadastro de usuários
* Consulta de usuário por ID
* Exclusão de usuário
* Retorno em JSON
* Tratamento básico de erros

## 📉 Tecnologias Utilizadas

* Java 17+
* Spring Boot 3.x
* Maven
* Spring Web
* Spring Data JPA
* H2 Database (ou outro banco de sua escolha)
* Jakarta Servlet API

## 🔧 Endpoints da API

### ✍️ Criar Usuário

* **URL:** `POST /v1/usuarios`
* **Body:** JSON com os campos `nome`, `email`, `senha`

```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "123456"
}
```

* **Resposta:**

  * `201 Created`
  * Body: `Usuário criado com sucesso. ID: 52`

### 🔍 Consultar Usuário por ID

* **URL:** `GET /v1/usuarios/{usuarioId}`
* **Resposta:**

```json
{
  "id": 52,
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "123456"
}
```

* **Erros:**

  * `400 Bad Request`: ID inválido ou usuário não encontrado

### 🗑️ Deletar Usuário

* **URL:** `DELETE /v1/usuarios/{usuarioId}`
* **Resposta:**

  * `200 OK`: `Usuário deletado com sucesso.`
  * `400 Bad Request`: ID inválido

## 📁 Estrutura do Projeto

* `controller`: Camada REST (`UsuarioController`)
* `service`: Regras de negócio (`UsuarioService`)
* `repository`: Integração com banco via Spring Data JPA
* `entity`: Classe de domínio (`Usuario`)
* `dto`: Objetos de transferência (`CriarUsuarioDTO`, `UsuarioDTO`)

## ⚠️ Observações

* Os IDs são `long` e atualmente gerados aleatoriamente.
* A senha é retornada no DTO apenas para fins de exemplo.

  * **Não recomendado** em produção: use criptografia e omita senhas da resposta.

## 💻 Como Executar

```bash
# Clonar repositório
git clone https://github.com/seu-usuario/app-servicos-brno.git

# Entrar na pasta
cd app-servicos-brno

# Executar com Maven
./mvnw spring-boot:run
```

A aplicação estará disponível em:
`http://localhost:8080/v1/usuarios`

---

## ✍️ Autor

* **Bruno de Jesus Silva**
* \https://www.linkedin.com/in/bruno-silva-4746b7187/
