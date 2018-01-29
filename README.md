# mcf
## Outline
## アプリサーバの動かし方
1. トップ階層のpom.xmlに対して`mvn install`を行う。
これでmcflibを内部的に読み込む。
1. mcfapp階層で`mvn spring-boot:run`を行うとサーバが立ちがる。
1. `localhost:8081/sample`にアクセスし、webページが表示されたらOK
