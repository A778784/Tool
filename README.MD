# 概要

"Getter.exe" は以下の機能を持ったツールです。<br>
・ネットワークPCのXXXFileの生成を監視し、ローカルにコピー後、Fileを整形して保存。<br>
・指定値があれば、テーブルの指定値を画面上ですぐに確認できるようにする。<br>

※XXXFileとは、ある企業のシステムで生成されるファイルを指します。<br>
　当システムが生成するデータを対象としたツールになり、汎用的なツールではありません。

# 目的-課題の解決
・File手動採取の工数削減<br>
・エビデンス精度を落とさず、従来のエクセルFile展開の工数削減<br>
・指定値確認の高速化<br>

# 今後実装検討の機能
・採取したデータのDB化<br>

# 概要機能フロー
1．指定IPにネットワーク接続し、XXXFileの生成を監視<br>
2．XXXFileをローカルにコピー<br>
3．XXXFileをAAA.exeに渡し、解凍<br>
4．解凍された複数のTBLファイルを各対応するHeaderを参照し、Headerをセット<br>
5．iniFileに指定されているValueがあれば、コンソールに表示、指定された値をファイルに保存します。<br>


# 事前設定
<strong>Directory</strong><br>
C直下に下記階層を作ってください<BR>
<pre>
└─999_JavaTools<br>
        ├─001_Getter<br>
         └─000_設定<br>
           │  targetValue.ini<br>
           ├─HeaderFiles<br>
           │      01_XXX_Value_Name.txt<br>
           │      02_YYY_Value_Name.txt<br>
           │      03_ZZZ_Value_Name.txt<br>
           │      04_AAA_Value_Name.txt<br>
           │      05_BBB_Value_Name.txt<br>
           │      06_CCC_Value_Name.txt<br>
           │
           └─AAA<br>
             └─AAA.exe
</pre><br>

<strong>特定のフィールド抽出について</strong><br>
<dl>
    <strong><dt>iniFileでフラグ（0：動作しない/1:動作する）を設定し、抽出値を指定します。</dt></strong>
    <dd>Requirementに記載の設定ファイルを用意。</dd>
    <dd>指定した値を採取、表示したい場合は、iniFileの　0:CommonFlag	=1 を「1」に設定</dd>
    <dd>採取したいテーブルのFlagを「1」に設定し、採取したいValueのフィールド値をセットする。</dd>
</dl>

<dl>

設定例
<em>
<pre>
<dd>0:CommonFlag	=1</dd>
<dd>1:XXXFlag	=1 </dd>
<dd>2:YYYFlag	=1 </dd>
<dd>3:ZZZFlag	=1 </dd>
<dd>4:AAAFlag	=1 </dd>
<dd>5:BBBFlag	=0 </dd>
<dd>6:CCCFlag	=0 </dd>
<dd>7:XXX_Value =10,90 </dd>
<dd>8:YYY_Value =6,8 </dd>
<dd>9:ZZZ_Value =5,7,50 </dd>
<dd>11:BBB_Value =9,10 </dd>
<dd>10:AAA_Value =2,3 </dd>
<dd>12:CCC_VALUE =4,5 </dd>
</em>
</dl>
</pre>


# 使用フロー
1.exeを起動<br>
2.コンソール画面のプロンプトが表示される。IP入力<br>
3.コンソール画面のプロンプトが表示される。YYYYMMDD入力。※当日でよければ「N」入力<br>
4.監視開始<br>
使用イメージ：https://github.com/A778784/Tool/blob/main/%E3%82%A4%E3%83%A1%E3%83%BC%E3%82%B8.jpg


# Note
機密情報保持の為、ソース内、ドキュメント内の対象File名、アウトプット詳細を全て伏せさせていただいております。<br>
特定のシステム（特定のデータ出力）のみに有効なツールである為、再現にはダミーデータが必要です。<br>
ツール起動にはダミーデータによって、データ項目の持ち方を再現する必要がありますが、<br>
データ項目の再現は機密保持に抵触する可能性がある為、テストバージョンは未作成です。
