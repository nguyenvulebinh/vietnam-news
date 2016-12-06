for file in /home/hieuapp/vitk-input/*;
do
	if [[ -d $file ]]; then
	    echo "$file is a directory"

	    for item in $file/*;
		do
			echo "$item processing..."
			if [[ -d $item ]]; then
				echo "$item scanning.."

				for news in $item/*;
				do
					echo "------------------------------"
					echo "STARTING...with file = "$news
					echo "------------------------------"
					fileName=$(basename $item )
					folder=$(basename $file )
					dirOutput="/home/hieuapp/vitk-output/"$folder"/"$fileName
					~/spark/bin/spark-submit --class=vn.vitk.Vitk ~/vn.vitk/target/vn.vitk-3.0.jar -i $item -o $dirOutput

					echo "------------------------------"
					echo "DONE. File output in "$dirOutput
					echo "------------------------------"
				done

			elif [[ -f $item ]]; then
				echo "$item is a file"
			else
				echo "$item is not valid 1"
	    		exit 1
			fi
		done

	elif [[ -f $file ]]; then
	    echo "$file is a file"
	else
	    echo "$file is not valid"
	    exit 1
	fi
done