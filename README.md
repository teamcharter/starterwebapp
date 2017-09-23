# Simple Web Application

## Instructions to Run

### For Testing
- Navigate to the root folder in your command line and serve the web app.
```
python -m SimpleHTTPServer 8000
python -m http.server 8000
```
- Web app will be available at http://localhost:8000
- If the backend is needed, start it on another port.
```
javac -cp ".:lib/*"" server/Main.java
java -cp ".:lib/*"" server.Main
```
- Backend API will be available at http://localhost:8080

### For Demo
- Push changes to the branch of your repository that builds the GitHub pages site.
- Open the backend in Cloud9 and start the server there, a public URL will be generated.