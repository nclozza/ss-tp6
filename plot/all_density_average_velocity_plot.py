import matplotlib.pyplot as plt
import math

denstiy_average_velocity_path_1 = 'parameters 1/fundamentalData.csv'
denstiy_average_velocity_path_2 = 'parameters 2/fundamentalData.csv'
denstiy_average_velocity_path_3 = 'parameters 3/fundamentalData.csv'
denstiy_average_velocity_path_4 = 'parameters 4/fundamentalData.csv'
denstiy_average_velocity_path_5 = 'parameters 5/fundamentalData.csv'
denstiy_average_velocity_path_6 = 'parameters 6/fundamentalData.csv'

denstiy_average_velocity_file_1 = open(denstiy_average_velocity_path_1,'r')
denstiy_average_velocity_file_2 = open(denstiy_average_velocity_path_2,'r')
denstiy_average_velocity_file_3 = open(denstiy_average_velocity_path_3,'r')
denstiy_average_velocity_file_4 = open(denstiy_average_velocity_path_4,'r')
denstiy_average_velocity_file_5 = open(denstiy_average_velocity_path_5,'r')
denstiy_average_velocity_file_6 = open(denstiy_average_velocity_path_6,'r')

denstiy_average_velocity_array_1 = denstiy_average_velocity_file_1.read().split('\n')
denstiy_average_velocity_array_2 = denstiy_average_velocity_file_2.read().split('\n')
denstiy_average_velocity_array_3 = denstiy_average_velocity_file_3.read().split('\n')
denstiy_average_velocity_array_4 = denstiy_average_velocity_file_4.read().split('\n')
denstiy_average_velocity_array_5 = denstiy_average_velocity_file_5.read().split('\n')
denstiy_average_velocity_array_6 = denstiy_average_velocity_file_6.read().split('\n')

denstiy_average_velocity_list_1 = list(map(lambda x: x.split(","), denstiy_average_velocity_array_1))
denstiy_average_velocity_list_2 = list(map(lambda x: x.split(","), denstiy_average_velocity_array_2))
denstiy_average_velocity_list_3 = list(map(lambda x: x.split(","), denstiy_average_velocity_array_3))
denstiy_average_velocity_list_4 = list(map(lambda x: x.split(","), denstiy_average_velocity_array_4))
denstiy_average_velocity_list_5 = list(map(lambda x: x.split(","), denstiy_average_velocity_array_5))
denstiy_average_velocity_list_6 = list(map(lambda x: x.split(","), denstiy_average_velocity_array_6))

denstiy_average_velocity_file_1.close()
denstiy_average_velocity_file_2.close()
denstiy_average_velocity_file_3.close()
denstiy_average_velocity_file_4.close()
denstiy_average_velocity_file_5.close()
denstiy_average_velocity_file_6.close()

denstiy_average_velocity_1 = []
denstiy_average_velocity_2 = []
denstiy_average_velocity_3 = []
denstiy_average_velocity_4 = []
denstiy_average_velocity_5 = []
denstiy_average_velocity_6 = []
density_array_1 = []
density_array_2 = []
density_array_3 = []
density_array_4 = []
density_array_5 = []
density_array_6 = []

for i in range(0, len(denstiy_average_velocity_list_1)):
  denstiy_average_velocity_1.append(float(denstiy_average_velocity_list_1[i][1]))
  density_array_1.append(float(denstiy_average_velocity_list_1[i][0]))

for i in range(0, len(denstiy_average_velocity_list_2)):
  denstiy_average_velocity_2.append(float(denstiy_average_velocity_list_2[i][1]))
  density_array_2.append(float(denstiy_average_velocity_list_2[i][0]))

for i in range(0, len(denstiy_average_velocity_list_3)):
  denstiy_average_velocity_3.append(float(denstiy_average_velocity_list_3[i][1]))
  density_array_3.append(float(denstiy_average_velocity_list_3[i][0]))

for i in range(0, len(denstiy_average_velocity_list_4)):
  denstiy_average_velocity_4.append(float(denstiy_average_velocity_list_4[i][1]))
  density_array_4.append(float(denstiy_average_velocity_list_4[i][0]))

for i in range(0, len(denstiy_average_velocity_list_5)):
  denstiy_average_velocity_5.append(float(denstiy_average_velocity_list_5[i][1]))
  density_array_5.append(float(denstiy_average_velocity_list_5[i][0]))

for i in range(0, len(denstiy_average_velocity_list_6)):
  denstiy_average_velocity_6.append(float(denstiy_average_velocity_list_6[i][1]))
  density_array_6.append(float(denstiy_average_velocity_list_6[i][0]))

prom_1 = []
prom_2 = []
prom_3 = []
prom_4 = []
prom_5 = []
prom_6 = []
density_1 = []
density_2 = []
density_3 = []
density_4 = []
density_5 = []
density_6 = []

for i in range(0, int(len(denstiy_average_velocity_1) / 3)):
  prom_1.append((denstiy_average_velocity_1[i*3] + denstiy_average_velocity_1[(i*3)+1] + denstiy_average_velocity_1[(i*3)+2]) / 3)
  density_1.append(density_array_1[i*3])

for i in range(0, int(len(denstiy_average_velocity_2) / 3)):
  prom_2.append((denstiy_average_velocity_2[i*3] + denstiy_average_velocity_2[(i*3)+1] + denstiy_average_velocity_2[(i*3)+2]) / 3)
  density_2.append(density_array_2[i*3])

for i in range(0, int(len(denstiy_average_velocity_3) / 3)):
  prom_3.append((denstiy_average_velocity_3[i*3] + denstiy_average_velocity_3[(i*3)+1] + denstiy_average_velocity_3[(i*3)+2]) / 3)
  density_3.append(density_array_3[i*3])

for i in range(0, int(len(denstiy_average_velocity_4) / 3)):
  prom_4.append((denstiy_average_velocity_4[i*3] + denstiy_average_velocity_4[(i*3)+1] + denstiy_average_velocity_4[(i*3)+2]) / 3)
  density_4.append(density_array_4[i*3])

for i in range(0, int(len(denstiy_average_velocity_5) / 3)):
  prom_5.append((denstiy_average_velocity_5[i*3] + denstiy_average_velocity_5[(i*3)+1] + denstiy_average_velocity_5[(i*3)+2]) / 3)
  density_5.append(density_array_5[i*3])

for i in range(0, int(len(denstiy_average_velocity_6) / 3)):
  prom_6.append((denstiy_average_velocity_6[i*3] + denstiy_average_velocity_6[(i*3)+1] + denstiy_average_velocity_6[(i*3)+2]) / 3)
  density_6.append(density_array_6[i*3])

plt.plot(density_6, prom_6, label="W = 2 * (MAX_R + MIN_R)")
plt.plot(density_5, prom_5, label="W = 4 * (MAX_R + MIN_R)")
plt.plot(density_1, prom_1, label="W = 5 * (MAX_R + MIN_R)")
plt.plot(density_3, prom_3, label="W = 6 * (MAX_R + MIN_R)")
plt.plot(density_4, prom_4, label="W = 10 * (MAX_R + MIN_R)")
# plt.plot(density_2, prom_2, label="")

FONTSIZE = 20
plt.legend(loc='top right', fontsize=FONTSIZE)
plt.ylabel('Average velocity [m/s]', fontsize=FONTSIZE)
plt.xlabel('Density [1/m²]', fontsize=FONTSIZE)
plt.tick_params(axis='both', which='major', labelsize=FONTSIZE)

plt.show()