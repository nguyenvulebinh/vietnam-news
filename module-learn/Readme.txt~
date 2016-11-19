# DATA-TRAIN
Mô tả về tập dữ liệu

1. Cau truc folder data-train
- bag_word (Folder chứa toàn bộ dữ liệu tranning đã tách từ, loại bỏ từ dừng, tính if và idf)
  -- Cong_nghe (folder chứa các tài liệu cùng nhãn)
    --- part-00000 (túi từ của từng văn bản)
    --- ...
    --- part-n
  --Giai_tri 
    --- part-00000 
    --- ...
    --- part-n
 --dictionary.txt (tập từ điển của data train)
 --tf.txt (vector tf của tất cả văn bản)
 --idf.txt (vector idf của các từ trong từ điển)
 
# TF
vd:
855 (số lượng văn bản)
11 (số chiều của vector biểu diễn văn bản)
dân_trí grammy thứ nghệ_sĩ giành thềm trao lịch_sử giải hãy nhìn Giai_tri (word1, word2, ...,wordn, nhãn)
1 2 1 1 1 1 1 1 2 1 1 (tần xuất các từ trong vb)
...

# IDF
17865 (kích thước từ điển)
dân_trí grammy thứ nghệ_sĩ giành thềm trao lịch_sử giải hãy nhìn (danh sách các từ)
2.43 4.96 6.06 6.06 3.49 6.06 6.06 5.14 2.43 4.96 6.06 (IDF tương ứng)

Cong thuc tinh idf:
idf(t) = log( |D| /(1+ |{d: t in d}|) )

