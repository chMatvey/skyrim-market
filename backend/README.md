### Docker

    docker build -t skyrim-market-backend .
    
    docker run --name skyrim-market-backend -d -p 8080:8080 --env-file env-file skyrim-market-backend