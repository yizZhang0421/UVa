1.這個問題可以巧妙的轉換成最短路徑問題。 x1 到 xN 看作是圖上的 N 個點，一條 xi-xj≤c 的限制式子看作是一條 xj 到 xi 的邊，其權重是 c 。<br>
如果無解，那麼圖上有負環。如果有解，那麼圖上各點的最短路徑長度就是其中一組解。<br>
(http://www.csie.ntnu.edu.tw/~u91029/Simulation.html#2)

2.prefix sum: <br>
se=[a, b, c, d, e, f]<br>
Si=sum of index of se from 0 to indexOf i<br>
sum=[Sa, Sb, Sc, Sd, Se, Sf]<br>
如果要得到b到e的總和：Se-Sb-1 => Se-Sa<br>

測資中<br>
se=[A1, A2, A3, A4]<br>
Sum=[S0, S1, S2, S3, S4]<br>

1 2 gt 0 <br>
-> A1+A2+A3>0 <br>
-> S3-S0>0 <br>
-> S0-S3<0 (同乘-1變號)<br>
-> S0-S3<=-1<br>

2 2 lt 2<br>
-> A2+A3+A4<2<br>
-> S4-S1<2<br>
-> S4-S1<=1<br>

3.Johnson's algorithm： all pair shortest path<br>
增加一個節點到圖中每個點距離為0<br>
跑一次bellman-ford<br>
該過程就可以檢查負環，即繞某個環的距離和為負數，無限繞可以得到無限小<br>
最長的路徑為N-1，只要檢查大於N-1為陷在負環中。<br>
下一步將新增的點得到的最短距離作為各節點的權重<br>
W'(i, j)=W(i, j)+i的權重-j的權重<br>
會得到一個不存在負權的圖，再跑Dijkstra就可以得到所有的最短路徑<br>
