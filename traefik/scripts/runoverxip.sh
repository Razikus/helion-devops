docker run --network web --name $1 --label traefik.docker.network=web --label traefik.enable=true --label traefik.basic.frontend.rule=Host:$1.127.0.0.1.xip.io --label traefik.basic.port=$2 --label traefik.basic.protocol=http ${@:3}

