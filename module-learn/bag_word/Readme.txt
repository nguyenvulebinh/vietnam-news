# data-trai
Mo ta ve tap du lieu
1. Cau truc folder data-train
- bag_word (Folder chua toan bo du lieu tranning)
  -- Ban_doc (tap hop cac tai lieu cung nhan)
    --- part-00000 (tui tu cua tung van ban)
    --- ...
    --- part-n
  --Chuyen_la (tap hop cac tai lieu cung nhan)
    --- part-00000 (tui tu cua tung van ban)
    --- ...
    --- part-n
 --dictionary.txt (tap tu dien cua tap data-train)
 --tf.txt (vector tf cua tat ca vb)
 --idf.txt (vector idf cua cac tu trong bo tu dien)
 
# tf
vd.
855 (so luong van ban)
11 (so chieu cua vector bieu dien van ban)
dân_trí grammy thứ nghệ_sĩ giành thềm trao lịch_sử giải hãy nhìn (danh sach cac tu)
1 2 1 1 1 1 1 1 2 1 1 (tan xuat cac tu)
...

# idf
17865 (kich thuoc tu dien)
dân_trí grammy thứ nghệ_sĩ giành thềm trao lịch_sử giải hãy nhìn (danh sach cac tu)
2.43 4.96 6.06 6.06 3.49 6.06 6.06 5.14 2.43 4.96 6.06 (idf tuong ung)

Cong thuc tinh idf:
idf(t) = log( |D| /(1+ |{d: t in d}|) )

