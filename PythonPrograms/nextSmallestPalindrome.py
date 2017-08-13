n = input()

for i in range(n):
    number = raw_input()

    if number.count('9') == len(number):
        print str(pow(10, len(number)) + 1)

    elif int(number) < 9:
        print int(number) + 1

    else:
        baseNumber = int(number)
        substr = number[0:len(number)/2 + len(number) % 2]
        a = int(substr)

        if len(number) %2 == 1:
            output = str(a) + str(a)[::-1][1:]
        else:
            output = str(a) + str(a)[::-1]


        if int(output) > baseNumber:
            print output
        else:
            a += 1
            if len(number) %2 == 1:
                output = str(a) + str(a)[::-1][1:]
            else:
                output = str(a) + str(a)[::-1]


            if int(output) > baseNumber:
                print output
