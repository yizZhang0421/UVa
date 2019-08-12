先有排序中嘗試各個"間"遞迴下去</br>
ex:已排好的list={a,c,d,e}</br>
要排進去的=f</br>
a c d e f => if e<f</br>
a c d f e => else if d<f  (f<e && d<f)</br>
a c f d e => else if c<f</br>
a f c d e => else if a<f</br>
f a c d e => else</br>
</br>
初始list為第一個字元</br>
</br>
例外：當只有一個直接writeln()第一個，判斷式跳過</br>