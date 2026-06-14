# =====================================================
# Dockerfile — Frontend React (MediAgenda)
# Etapa 1: build con Node + Vite
# Etapa 2: servir con Nginx Alpine
# =====================================================

FROM node:20-alpine AS build
WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# ---- Imagen de producción ----
FROM nginx:1.25-alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
