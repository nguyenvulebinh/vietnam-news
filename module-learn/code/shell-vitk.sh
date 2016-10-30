for file in ~/vitk-input/Dân_trí/*;
do
	if [[ -d $file ]]; then
	    echo "$file is a directory"

	    for item in $file/*;
	    do
			echo "------------------------------"
			echo "STARTING...with file = "$item
			echo "------------------------------"
			fileName=$(basename $file .txt)
			dirOutput="/home/hieuapp/vitk-output/"$fileName
			~/spark/bin/spark-submit --class=vn.vitk.Vitk ~/vn.vitk/target/vn.vitk-3.0.jar -i $file -o $dirOutput

			echo "------------------------------"
			echo "DONE. File output in "$dirOutput
			echo "------------------------------"
	    done

	elif [[ -f $file ]]; then
	    echo "$file is a file"
	else
	    echo "$file is not valid"
	    exit 1
	fi
done