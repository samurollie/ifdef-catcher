# FROM fedora:32

# RUN dnf install -y wget python3 python3-pip python3-devel gcc gcc-c++ make cmake git python2.7

# Install srcML
# RUN wget http://131.123.42.38/lmcrs/v1.0.0/srcml-1.0.0-1.fc32.x86_64.rpm
# RUN dnf install -y srcml-1.0.0-1.fc32.x86_64.rpm
# RUN rm srcml-1.0.0-1.fc32.x86_64.rpm

# Install pipenv
# RUN pip install --user pipenv

# Install cppstats
# RUN git clone https://github.com/se-sic/cppstats.git
# RUN cd cppstats && sudo python setup.py install
# RUN cd .. && rm -rf cppstats
FROM jcvf/cppstats:latest

# RUN apt-get update && apt-get install -y python3-pip python3-dev gcc g++ make cmake git python2.7
RUN apt update && apt install -y curl
RUN curl https://bootstrap.pypa.io/pip/2.7/get-pip.py --output get-pip.py
RUN python2 get-pip.py
# RUN pip --version

RUN pip install --user pipenv

ENV PATH="/root/.local/bin:${PATH}"
ENV LANG=C.UTF-8

WORKDIR /home

COPY requirements.txt .

# RUN python -m venv venv
# RUN pipenv --python /usr/bin/python
# RUN pipenv install

# RUN source venv/bin/activate
# RUN virtualenv venv
# RUN source venv/bin/activate
RUN pip install -r requirements.txt
# RUN pip install wget
# RUN pip install gdown

COPY . .
