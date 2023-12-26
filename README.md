# API de Gerenciamento de Usuários e Transações Baseado no Desafio Pic pay

Esta é uma API RESTful para gerenciamento de usuários e transações. Permite a criação de novos usuários, consulta de usuários existentes e realização de transações entre eles.

## Endpoints Disponíveis

### Usuários

#### Recuperar todos os usuários
- **Método:** GET
- **Endpoint:** `/usuarios`
- **Descrição:** Retorna todos os usuários cadastrados no sistema.

#### Criar um novo usuário
- **Método:** POST
- **Endpoint:** `/usuarios`
- **Descrição:** Cria um novo usuário com os seguintes parâmetros no corpo da requisição:
 ```
 {
    "nome": "Nome do Usuário",
    "cpf": "12345678901",
    "email": "usuario@example.com",
    "senha": "senha123",
    "saldo": 100.0,
    "tipo": "COMUM"
}
```
O tipo deve ser COMUM ou LOJISTA.

### Transações

#### Realizar uma transação
- **Método:** POST
- **Endpoint:** `/transaction`
- **Descrição:** Realiza uma transação entre dois usuários, passando os seguintes parâmetros no corpo da requisição:
```
 {
    "valorTransferencia": 100.0,
    "payer_id": 1,
    "payee_id": 2
}
```

Obs: Usuários do tipo LOJISTAS só recebem dinheiro, apenas usuários do tipo COMUM podem enviar e receber dinheiro.

## Configuração Padrão

- **URL Base:** `http://localhost:8080`
- **Banco de Dados:** H2 (em memória)

## Requisitos

Certifique-se de ter o Java e as dependências do projeto instaladas para executá-lo.

## Executando o Projeto

1. Clone o repositório.
2. Configure as dependências e o ambiente de acordo com o arquivo de configuração fornecido.
3. Execute a aplicação.
4. Use as rotas mencionadas acima para interagir com a API.

