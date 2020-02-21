file = open("input").read();
arr = list(file)
sum = 0

def findOppositeNumber(i, len):
    oppositeIndex = (i + len//2) % len
    return arr[oppositeIndex]

for i in range(len(arr)-1):
    if arr[i] is findOppositeNumber(i,len(arr)):
        sum += int(arr[i])

print(sum)