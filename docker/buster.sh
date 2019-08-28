 apt update
 apt -y install apt-transport-https ca-certificates curl gnupg2 software-properties-common
 curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -
 add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/debian \
   $(lsb_release -cs) \
   stable"
deb [arch=amd64] https://download.docker.com/linux/debian buster stable
apt update
apt -y install docker-ce docker-compose
/sbin/usermod -aG docker $USER
