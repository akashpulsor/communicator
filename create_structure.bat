

@echo off
SET ROOT=communicator-ui

REM Create root folder
mkdir %ROOT%
cd %ROOT%

REM Create main files
echo > .env
echo > index.html
echo > package.json
echo > vite.config.js

REM Create src structure
mkdir src
cd src
echo > main.jsx
echo > App.jsx
echo > styles.css

REM Create app folder
mkdir app
cd app
echo > store.js
cd ..

REM Create services folder
mkdir services
cd services
echo > api.js
echo > authApi.js
echo > documentsApi.js
echo > sessionsApi.js
echo > historyApi.js
echo > useRealtime.js
cd ..

REM Create pages folder
mkdir pages
cd pages
echo > Login.jsx
echo > Register.jsx
echo > Chat.jsx
echo > UploadPdf.jsx
echo > History.jsx
cd ..

REM Create components folder
mkdir components
cd components
echo > Navbar.jsx
echo > MessageBubble.jsx
cd ../..

echo Project structure created successfully!
pause
