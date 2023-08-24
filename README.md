# myphotosnameapp-webbackend
Project to connect all services for the Web application Services

# Instruction to create Docker Image 
docker build --no-cache -t myphotosnameapp-webbackend-v1-0 .

# Run the Docker container based on the image
docker run -p 8086:8086 myphotosnameapp-webbackend-v1-0
