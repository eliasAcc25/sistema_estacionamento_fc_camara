# 🚀 Como Subir o Projeto Sistema de Estacionamento para o GitHub

## 📋 Pré-requisitos
- Conta no GitHub
- Git instalado no seu computador
- Projeto funcionando localmente

## 🛠️ Passo a Passo Completo

### **PARTE 1: Preparação Local**

#### 1. **Abrir o Terminal no Diretório do Projeto**
```bash
# Navegue até a pasta do projeto
cd "C:\Users\elias.andre.torres\Downloads\sistem_estacionamento\sistem_estacionamento"
```

#### 2. **Inicializar o Git (se ainda não foi feito)**
```bash
git init
```

#### 3. **Configurar seu usuário Git (se ainda não fez)**
```bash
git config --global user.name "Seu Nome"
git config --global user.email "seu.email@exemplo.com"
```

#### 4. **Adicionar todos os arquivos**
```bash
git add .
```

#### 5. **Fazer o primeiro commit**
```bash
git commit -m "feat: Sistema de Estacionamento FCamara - CRUD completo com Swagger"
```

### **PARTE 2: Criação do Repositório no GitHub**

#### 1. **Acessar o GitHub**
- Vá para: https://github.com
- Faça login na sua conta

#### 2. **Criar Novo Repositório**
- Clique no botão **"New"** ou **"+"** no canto superior direito
- Selecione **"New repository"**

#### 3. **Configurar o Repositório**
- **Repository name**: `sistema-estacionamento-fcamara`
- **Description**: `Sistema de gerenciamento de estacionamento para carros e motos - Teste técnico FCamara`
- **Visibilidade**: 
  - ✅ **Public** (recomendado para portfólio)
  - ❌ Private (se preferir privado)
- **Inicialização**:
  - ❌ **NÃO** marque "Add a README file"
  - ❌ **NÃO** marque "Add .gitignore"
  - ❌ **NÃO** marque "Choose a license"
- Clique em **"Create repository"**

### **PARTE 3: Conectar e Enviar o Código**

#### 1. **Adicionar o Repositório Remoto**
```bash
# Substitua 'SEU_USUARIO' pelo seu nome de usuário do GitHub
git remote add origin https://github.com/SEU_USUARIO/sistema-estacionamento-fcamara.git
```

#### 2. **Renomear a Branch Principal (se necessário)**
```bash
git branch -M main
```

#### 3. **Enviar o Código para o GitHub**
```bash
git push -u origin main
```

### **PARTE 4: Verificação**

#### 1. **Confirmar no GitHub**
- Atualize a página do seu repositório
- Verifique se todos os arquivos apareceram:
  - ✅ README.md
  - ✅ pom.xml
  - ✅ src/
  - ✅ GUIA_TESTES.md
  - ✅ postman_collection.json

#### 2. **Verificar se o README aparece formatado**
- O GitHub deve mostrar seu README.md formatado na página inicial

## 🔄 Para Atualizações Futuras

### **Quando fizer mudanças no código:**
```bash
# 1. Adicionar mudanças
git add .

# 2. Fazer commit com mensagem descritiva
git commit -m "fix: corrigir problema de validação de placa duplicada"

# 3. Enviar para o GitHub
git push
```

## 🎯 Exemplos de Mensagens de Commit

- `feat: adicionar validação de campos obrigatórios`
- `fix: corrigir erro de conexão com banco H2`
- `docs: atualizar documentação da API`
- `refactor: melhorar estrutura do código`
- `test: adicionar testes para CRUD de veículos`

## 🚨 Problemas Comuns e Soluções

### **Erro: "remote origin already exists"**
```bash
git remote rm origin
git remote add origin https://github.com/SEU_USUARIO/sistema-estacionamento-fcamara.git
```

### **Erro de autenticação**
- Use **Personal Access Token** ao invés da senha
- Configure no GitHub: Settings → Developer settings → Personal access tokens

### **Arquivos muito grandes**
- Verifique se o .gitignore está correto
- O banco H2 (data/) não deve ir para o GitHub

## 📱 GitHub Desktop (Alternativa Mais Fácil)

Se preferir interface gráfica:

1. **Baixe o GitHub Desktop**: https://desktop.github.com
2. **Instale e faça login**
3. **Clique em "Add an Existing Repository"**
4. **Selecione a pasta do projeto**
5. **Clique em "Publish repository"**

## ✅ Checklist Final

- [ ] Repositório criado no GitHub
- [ ] Código enviado com sucesso
- [ ] README.md aparece formatado
- [ ] Arquivos desnecessários não foram enviados (.gitignore funcionando)
- [ ] Repositório está público (para portfólio)
- [ ] Link do repositório salvo para compartilhar

## 🌟 Resultado Final

Seu projeto estará disponível em:
```
https://github.com/SEU_USUARIO/sistema-estacionamento-fcamara
```

**🎉 Pronto! Seu projeto profissional está no GitHub e pode ser usado no seu portfólio!**
