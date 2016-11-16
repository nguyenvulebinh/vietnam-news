
file=$1;
fileName=$(basename $file .txt)
dirOutput="/home/thangld/Xu_li_ngon_ngu/vitk-output/"$fileName
~/Xu_li_ngon_ngu/spark/bin/spark-submit --class=vn.vitk.Vitk ~/Xu_li_ngon_ngu/vn.vitk/target/vn.vitk-3.0.jar -i $file